package io.fiap.erp.model.dto;

import lombok.Data;

@Data
public class ItemPedidoDTO {

    private Long idProduto;
    private Long idPedido;
    private Long quantidade;
}
