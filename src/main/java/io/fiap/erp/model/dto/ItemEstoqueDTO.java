package io.fiap.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueDTO {

    private Long id;
    private String descricao;
    private Long quantidadeAtual;
    private Long quantidadeIdeal;
    private LocalDateTime dataHoraUltimaAtualizacao;
}
