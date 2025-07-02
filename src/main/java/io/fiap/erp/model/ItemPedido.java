package io.fiap.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduto;
    private Long idPedido;
    private Long quantidade;

    public ItemPedido(){}

    public ItemPedido(Long idProduto, Long idPedido, Long quantidade){
        this.idProduto = idProduto;
        this.idPedido = idPedido;
        this.quantidade = quantidade;
    }
}
