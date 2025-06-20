package io.fiap.erp.model;

public enum TipoFuncionario {

    FUNCIONARIO(0, "GESTAO_PEDIDO,GESTAO_ESTOQUE,ATUALIZAR_CARDAPIO"),
    GERENTE(1, "FECHAMENTO_CAIXA");

    private Integer codigo;
    private String authorities;

    TipoFuncionario(Integer codigo, String authorities) {
        this.codigo = codigo;
        this.authorities = authorities;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
