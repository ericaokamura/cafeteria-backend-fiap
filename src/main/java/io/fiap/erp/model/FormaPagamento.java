package io.fiap.erp.model;
public enum FormaPagamento {

    PIX(0, "PIX"),
    CARTAO_DEBITO(1, "CARTÃO DÉBITO"),
    CARTAO_CREDITO(2, "CARTÃO CRÉDITO");

    private Integer codigo;
    private String descricao;

    FormaPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
