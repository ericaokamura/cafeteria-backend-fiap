package io.fiap.erp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private FormaPagamento formaPagamento;
    private boolean pagamentoAprovado;
    private String nomeCliente;
    private Long comanda;
    private Long mesa;
    @Enumerated(value = EnumType.STRING)
    private StatusPedido statusPedido;
    private LocalDateTime dataHoraPedido;
    private String comentarios;
}

