package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.clienteId = :clienteId")
    List<Pedido> findByClienteId(@Param("clienteId") Integer clienteId);

    @Query(value = "SELECT * FROM pedido WHERE producto = :producto", nativeQuery = true)
    List<Pedido> findByProducto(Producto producto);

    List<Pedido> findByEstado(String estado);
}