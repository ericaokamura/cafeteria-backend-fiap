package io.fiap.erp.mapper;

import io.fiap.erp.model.ItemPedido;
import io.fiap.erp.model.dto.ItemPedidoDTO;

import java.util.List;
import java.util.ArrayList;

public class ItemPedidoMapper {
    public static List<ItemPedido> convertDTOListToModelList(List<ItemPedidoDTO> dtos) {
        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoDTO dto : dtos) {
            ItemPedido item = new ItemPedido();
            item.setIdProduto(dto.getIdProduto());
            item.setQuantidade(dto.getQuantidade());
            item.setIdPedido(dto.getIdPedido());
            itens.add(item);
        }
        return itens;
    }
    public static List<ItemPedidoDTO> convertModelListToDTOList(List<ItemPedido> itens) {
        List<ItemPedidoDTO> dtos = new ArrayList<>();
        for (ItemPedido item : itens) {
            ItemPedidoDTO dto = new ItemPedidoDTO();
            dto.setIdPedido(item.getIdPedido());
            dto.setIdProduto(item.getIdProduto());
            dto.setQuantidade(item.getQuantidade());
            dtos.add(dto);
        }
        return dtos;
    }
}
