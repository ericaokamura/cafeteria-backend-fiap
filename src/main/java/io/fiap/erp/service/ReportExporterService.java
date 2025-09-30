package io.fiap.erp.service;

import io.fiap.erp.model.ItemPedido;
import io.fiap.erp.model.Pedido;
import io.fiap.erp.model.Produto;
import io.fiap.erp.repository.ItemPedidoRepository;
import io.fiap.erp.repository.PedidoRepository;
import io.fiap.erp.repository.ProdutoRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportExporterService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public byte[] exportarRelatorioVendas() throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório de Vendas");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id Pedido");
        header.createCell(1).setCellValue("Id Produto");
        header.createCell(2).setCellValue("Quantidade");
        header.createCell(3).setCellValue("Nome do Produto");
        header.createCell(4).setCellValue("Descrição do Produto");
        header.createCell(5).setCellValue("Valor Unitário do Produto");
        header.createCell(6).setCellValue("Subtotal");
        header.createCell(7).setCellValue("Data/hora do Pedido");

        List<Pedido> pedidos = pedidoRepository.findAll();
        final int[] rowNum = {1};
        for (Pedido pedido : pedidos) {
            List<ItemPedido> itensPedido = itemPedidoRepository.findAllByIdPedido(pedido.getId());
            itensPedido.forEach(i -> {
                Optional<Produto> produto = produtoRepository.findById(i.getIdProduto());
                Row row = sheet.createRow(rowNum[0]++);
                row.createCell(0).setCellValue(pedido.getId());
                row.createCell(1).setCellValue(i.getIdProduto());
                row.createCell(2).setCellValue(i.getQuantidade());
                row.createCell(3).setCellValue(produto.get().getNome());
                row.createCell(4).setCellValue(produto.get().getDescricao());
                row.createCell(5).setCellValue(produto.get().getValorUnitario());
                row.createCell(6).setCellValue(i.getQuantidade() * produto.get().getValorUnitario());
                row.createCell(7).setCellValue(pedido.getDataHoraPedido().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            });
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            workbook.close();
            return out.toByteArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            workbook.close();
            return null;
        }
    }
}