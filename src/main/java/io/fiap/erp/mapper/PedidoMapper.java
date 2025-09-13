package io.fiap.erp.mapper;

import io.fiap.erp.model.FormaPagamento;
import io.fiap.erp.model.Pedido;
import io.fiap.erp.model.StatusPedido;
import io.fiap.erp.model.dto.PedidoDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    public static Pedido convertDTOToModel(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setFormaPagamento(FormaPagamento.valueOf(dto.getFormaPagamento()));
        pedido.setPagamentoAprovado(dto.isPagamentoAprovado());
        pedido.setMesa(dto.getMesa());
        pedido.setComanda(dto.getComanda());
        pedido.setNomeCliente(dto.getNomeCliente());
        pedido.setComentarios(dto.getComentarios());
        return pedido;
    }

    public static PedidoDTO convertModelToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFormaPagamento(pedido.getFormaPagamento().name());
        dto.setPagamentoAprovado(pedido.isPagamentoAprovado());
        dto.setNomeCliente(pedido.getNomeCliente());
        dto.setMesa(pedido.getMesa());
        dto.setComanda(pedido.getComanda());
        dto.setStatusPedido(pedido.getStatusPedido().name());
        dto.setDataHoraPedido(pedido.getDataHoraPedido());
        dto.setComentarios(pedido.getComentarios());
        return dto;
    }

    public static List<Pedido> converDTOListToModelList(List<PedidoDTO> dtos) {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoDTO dto : dtos) {
            Pedido pedido = new Pedido();
            pedido.setFormaPagamento(FormaPagamento.valueOf(dto.getFormaPagamento()));
            pedido.setPagamentoAprovado(dto.isPagamentoAprovado());
            pedido.setMesa(dto.getMesa());
            pedido.setComanda(dto.getComanda());
            pedido.setNomeCliente(dto.getNomeCliente());
            pedido.setStatusPedido(StatusPedido.valueOf(dto.getStatusPedido()));
            pedido.setComentarios(dto.getComentarios());
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public static List<PedidoDTO> converModelListToDTOList(List<Pedido> pedidos) {
        List<PedidoDTO> dtos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoDTO dto = new PedidoDTO();
            dto.setFormaPagamento(pedido.getFormaPagamento().name());
            dto.setPagamentoAprovado(pedido.isPagamentoAprovado());
            dto.setMesa(pedido.getMesa());
            dto.setComanda(pedido.getComanda());
            dto.setNomeCliente(pedido.getNomeCliente());
            dto.setStatusPedido(pedido.getStatusPedido().name());
            dto.setDataHoraPedido(pedido.getDataHoraPedido());
            dto.setComentarios(pedido.getComentarios());
            dtos.add(dto);
        }
        return dtos;
    }
}
