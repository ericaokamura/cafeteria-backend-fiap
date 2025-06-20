package io.fiap.erp.service;

import io.fiap.erp.mapper.ItemPedidoMapper;
import io.fiap.erp.mapper.PedidoMapper;
import io.fiap.erp.model.FormaPagamento;
import io.fiap.erp.model.ItemPedido;
import io.fiap.erp.model.Pedido;
import io.fiap.erp.model.StatusPedido;
import io.fiap.erp.model.dto.PedidoDTO;
import io.fiap.erp.repository.ItemPedidoRepository;
import io.fiap.erp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoDTO salvarProduto(PedidoDTO pedidoDTO) {
        Pedido pedido = PedidoMapper.convertDTOToModel(pedidoDTO);
        pedido.setDataHoraPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.EM_PREPARO);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        pedidoDTO.getItensPedido().forEach(i -> {
            ItemPedido item = new ItemPedido();
            item.setIdProduto(i.getIdProduto());
            item.setQuantidade(i.getQuantidade());
            item.setIdPedido(pedidoSalvo.getId());
            itemPedidoRepository.save(item);
        });
        return PedidoMapper.convertModelToDTO(pedidoSalvo);
    }

    public PedidoDTO atualizarPedido(Long idPedido, PedidoDTO pedidoDTO) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);
        if(optionalPedido.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Pedido pedido = optionalPedido.get();
        pedido.setStatusPedido(StatusPedido.valueOf(pedidoDTO.getStatusPedido()));
        pedido.setFormaPagamento(FormaPagamento.valueOf(pedidoDTO.getFormaPagamento()));
        pedido.setPagamentoAprovado(pedidoDTO.isPagamentoAprovado());
        pedido.setComanda(pedidoDTO.getComanda());
        pedido.setMesa(pedidoDTO.getMesa());
        pedido.setNomeCliente(pedidoDTO.getNomeCliente());
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return PedidoMapper.convertModelToDTO(pedidoAtualizado);
    }

    public PedidoDTO retornarPedido(Long idPedido) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);
        if(optionalPedido.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Pedido pedido = optionalPedido.get();
        return PedidoMapper.convertModelToDTO(pedido);
    }
    public List<PedidoDTO> retornarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> dtos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if(!StatusPedido.CANCELADO.equals(pedido.getStatusPedido()) && !StatusPedido.FINALIZADO.equals(pedido.getStatusPedido())) {
                PedidoDTO dto = PedidoMapper.convertModelToDTO(pedido);
                dto.setItensPedido(ItemPedidoMapper.convertModelListToDTOList(itemPedidoRepository.findByIdPedido(pedido.getId())));
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public void deletarTodosPedidos() {
        pedidoRepository.findAll().forEach(p -> pedidoRepository.delete(p));
    }

    public PedidoDTO atualizarStatusPedido(Long idPedido, String statusPedido) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);
        if(optionalPedido.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Pedido pedido = optionalPedido.get();
        pedido.setStatusPedido(StatusPedido.valueOf(statusPedido));
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return PedidoMapper.convertModelToDTO(pedidoAtualizado);
    }
}
