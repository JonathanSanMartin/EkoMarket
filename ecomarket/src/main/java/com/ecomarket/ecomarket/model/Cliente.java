package com.ecomarket.ecomarket.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;           

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = true)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String telefono;
}
