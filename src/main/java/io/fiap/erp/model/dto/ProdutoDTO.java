package io.fiap.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String tags;
    private Double valorUnitario;
    private LocalDateTime dataHoraUltimaAtualizacao;
}
