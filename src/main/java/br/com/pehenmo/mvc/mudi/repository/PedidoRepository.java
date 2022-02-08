package br.com.pehenmo.mvc.mudi.repository;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.status = :status")
    List<Pedido> findByStatus(StatusPedido status);
}
