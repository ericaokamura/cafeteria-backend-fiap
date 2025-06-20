package io.fiap.erp.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {

    private Long id;
    private List<ItemPedidoDTO> itensPedido;
    private String formaPagamento;
    private boolean pagamentoAprovado;
    private String nomeCliente;
    private Long comanda;
    private Long mesa;
    private String statusPedido;
    private LocalDateTime dataHoraPedido;
}
