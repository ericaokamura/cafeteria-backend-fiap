package io.fiap.erp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private String nomeUsuario;
    private String senha;
    private String cpf;
    private String tipoFuncionario;
    private LocalDateTime dataHoraCadastro;

}
