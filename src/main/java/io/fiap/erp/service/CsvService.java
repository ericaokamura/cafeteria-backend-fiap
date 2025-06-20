package io.fiap.erp.service;

import io.fiap.erp.mapper.ItemEstoqueMapper;
import io.fiap.erp.mapper.ProdutoMapper;
import io.fiap.erp.model.ItemEstoque;
import io.fiap.erp.model.Produto;
import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.model.dto.ProdutoDTO;
import io.fiap.erp.repository.ItemEstoqueRepository;
import io.fiap.erp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.csv.*;

@Service
public class CsvService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;

    public List<ProdutoDTO> csvToProducts(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<ProdutoDTO> products = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord record : csvRecords) {
                ProdutoDTO product = new ProdutoDTO(
                        record.get("descricao"),
                        Double.parseDouble(record.get("valorUnitario")),
                        LocalDateTime.now()
                );
                produtoRepository.save(ProdutoMapper.convertDTOToModel(product));
                products.add(product);
            }

            return products;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public List<ItemEstoqueDTO> csvToItensEstoque(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<ItemEstoqueDTO> itens = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord record : csvRecords) {
                ItemEstoqueDTO item = new ItemEstoqueDTO(
                        Long.parseLong(record.get("id")),
                        record.get("descricao"),
                        Long.parseLong(record.get("quantidadeAtual")),
                        Long.parseLong(record.get("quantidadeIdeal")),
                        LocalDateTime.now()
                );
                ItemEstoque model = itemEstoqueRepository.save(ItemEstoqueMapper.convertDTOToModel(item));
                itens.add(ItemEstoqueMapper.convertModelToDTO(model));
            }

            return itens;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }

    }
}

