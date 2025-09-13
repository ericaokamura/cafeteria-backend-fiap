package io.fiap.erp.repository;

import io.fiap.erp.model.Pedido;
import io.fiap.erp.model.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {

    List<Pedido> findAllByStatusPedido(StatusPedido statusPedido, Pageable pageable);

    List<Pedido> findAllByComanda(Long comanda, Pageable pageable);

    List<Pedido> findAllByMesa(Long comanda, Pageable pageable);

    Pedido save(Pedido pedido);

    Optional<Pedido> findById(Long idPedido);

    void delete(Pedido p);

    Page<Pedido> findAll(Pageable pageable);

    List<Pedido> findAll();

}
