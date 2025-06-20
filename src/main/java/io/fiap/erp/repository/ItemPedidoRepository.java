package io.fiap.erp.repository;

import io.fiap.erp.model.ItemPedido;
import io.fiap.erp.model.dto.ItemPedidoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByIdPedido(Long idPedido);
}
