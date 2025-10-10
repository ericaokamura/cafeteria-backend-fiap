package io.fiap.erp.model;

import com.pgvector.PGvector;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(
        name = "vector_store",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"itemEstoqueId"})
        }
)
@Data
public class Embedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Long itemEstoqueId;

    private String metadata;

    @Column(length = 1000)
    private String content;

    @Column(columnDefinition = "vector(1536)")
    private PGvector embedding;

}


