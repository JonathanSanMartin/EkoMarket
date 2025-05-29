package com.ecomarket.ecomarket.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer clienteId;      

    @Column(nullable = false)
    private Date fechaPedido;

    @Column(nullable = false)
    private String estado;          // "Pendiente", "Enviado", "Entregado"

    @Column(nullable = false)
    private int cantidad;
}
