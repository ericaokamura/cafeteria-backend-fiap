package io.fiap.erp.repository;

import io.fiap.erp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByComanda(String comanda);

    List<Pedido> findAllByStatus(String status);

    List<Pedido> findAllByMesa(String mesa);
}
