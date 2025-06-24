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
                linkTo(methodOn(ClienteController.class).obtenerCliente(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).listar()).withRel("clientes"));
    }
}
