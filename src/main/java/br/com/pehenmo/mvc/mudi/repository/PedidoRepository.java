package br.com.pehenmo.mvc.mudi.repository;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Cacheable("pedidosByStatus")
    @Query("SELECT p FROM Pedido p WHERE p.status = :status")
    List<Pedido> findByStatus(StatusPedido status, Pageable pageable);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username AND p.status = :status")
    List<Pedido> findByStatusAndUsername(String username, StatusPedido status);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
    List<Pedido> findAllByUsuario(String username);
}
