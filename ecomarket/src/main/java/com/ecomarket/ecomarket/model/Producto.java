package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String codigoProducto; 

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoriaEco;   

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String proveedor;
}
