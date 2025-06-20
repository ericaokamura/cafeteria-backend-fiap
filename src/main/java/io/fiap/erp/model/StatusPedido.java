package io.fiap.erp.model;

public enum StatusPedido {

    EM_PREPARO(0, "EM_PREPARO"),
    CANCELADO(1, "CARTÃO CANCELADO"),
    FINALIZADO(2, "CARTÃO FINALIZADO");

    private Integer codigo;
    private String descricao;

    StatusPedido(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
