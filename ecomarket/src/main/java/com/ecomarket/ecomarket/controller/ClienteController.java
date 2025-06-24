package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.ClienteModelAssembler;
import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @Operation(summary = "Lista todos los clientes")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
        @ApiResponse(responseCode = "204", description = "Sin resultados")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obtener cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerCliente(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteModelAssembler.toModel(cliente));
    }

    @Operation(summary = "Buscar cliente por email")
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarPorEmail(@PathVariable String email) {
        Cliente cliente = clienteService.findByEmail(email);
        return cliente != null
                ? ResponseEntity.ok(cliente)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Registrar nuevo cliente")
    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.save(cliente);
        return ResponseEntity.status(201).body(nuevoCliente);
    }

    @Operation(summary = "Eliminar cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        if (clienteService.findById(id) == null) return ResponseEntity.notFound().build();
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

