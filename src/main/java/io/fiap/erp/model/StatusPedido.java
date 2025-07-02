package io.fiap.erp.model;

public enum StatusPedido {

    INICIADO(0, "INICIADO"),
    EM_PREPARO(1, "EM_PREPARO"),
    CANCELADO(2, "CARTÃO CANCELADO"),
    FINALIZADO(3, "CARTÃO FINALIZADO");

    private Integer codigo;
    private String descricao;

    StatusPedido(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
