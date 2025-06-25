package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.ClienteModelAssembler;
import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @Operation(summary = "Lista todos los clientes con HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
        @ApiResponse(responseCode = "204", description = "Sin resultados")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> listar() {
        List<EntityModel<Cliente>> clientes = clienteService.findAll()
            .stream()
            .map(clienteModelAssembler::toModel)
            .collect(Collectors.toList());
            
        if (clientes.isEmpty()) return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(CollectionModel.of(clientes,
            linkTo(methodOn(ClienteController.class).listar()).withSelfRel(),
            linkTo(methodOn(ClienteController.class).registrarCliente(null)).withRel("crear-cliente")));
    }

    @Operation(summary = "Obtener cliente por ID con HATEOAS")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerCliente(@PathVariable Integer id) {
        return clienteService.findById(id)
            .map(clienteModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar cliente por email con HATEOAS")
    @GetMapping("/email/{email}")
    public ResponseEntity<EntityModel<Cliente>> buscarPorEmail(@PathVariable String email) {
        return clienteService.findByEmail(email)
            .map(clienteModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevo cliente con HATEOAS")
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.save(cliente);
        
        EntityModel<Cliente> resource = clienteModelAssembler.toModel(nuevoCliente);
        
        return ResponseEntity
            .created(linkTo(methodOn(ClienteController.class)
                .obtenerCliente(nuevoCliente.getId())).toUri())
            .body(resource);
    }

    @Operation(summary = "Eliminar cliente por ID con HATEOAS")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        return clienteService.findById(id)
            .map(cliente -> {
                clienteService.delete(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}