package io.fiap.erp.repository;

import io.fiap.erp.model.Embedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmbeddingRepository extends JpaRepository<Embedding, UUID> {
    boolean existsByItemEstoqueId(Long itemEstoqueId);
}

