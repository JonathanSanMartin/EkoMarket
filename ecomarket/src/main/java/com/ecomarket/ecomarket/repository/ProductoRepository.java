package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    List<Producto> findByNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM producto WHERE codigoProducto = :codigoProducto", nativeQuery = true)
    List<Producto> findByCodigoProducto(@Param("codigoProducto") String codigoProducto);

    
    List<Producto> findByCategoriaEcoAndProveedor(String categoria, String proveedor);
}