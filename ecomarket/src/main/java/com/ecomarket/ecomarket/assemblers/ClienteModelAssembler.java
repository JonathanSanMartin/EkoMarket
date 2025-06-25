package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.ClienteController;
import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
            // Self link
            linkTo(methodOn(ClienteController.class)
                .obtenerCliente(cliente.getId())).withSelfRel()
                .withType("GET"),
            
            // Link to all clients
            linkTo(methodOn(ClienteController.class)
                .listar()).withRel("todos-clientes")
                .withType("GET"),
            
            // Search by email
            linkTo(methodOn(ClienteController.class)
                .buscarPorEmail(cliente.getEmail())).withRel("buscar-por-email")
                .withType("GET"),
            
            // Delete operation
            linkTo(methodOn(ClienteController.class)
                .eliminarCliente(cliente.getId())).withRel("eliminar")
                .withType("DELETE"),
            
            // Update operation (asumiendo que existe)
            linkTo(methodOn(ClienteController.class)
                .actualizarCliente(cliente.getId(), null)).withRel("actualizar")
                .withType("PUT")
        );
    }
}