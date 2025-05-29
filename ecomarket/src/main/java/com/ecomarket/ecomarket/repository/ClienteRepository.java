package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    List<Cliente> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM cliente WHERE nombreCompleto =:nombreCompleto", nativeQuery = true)
    List<Cliente> findByNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

    List<Cliente> findByDireccion(String direccion);
}
