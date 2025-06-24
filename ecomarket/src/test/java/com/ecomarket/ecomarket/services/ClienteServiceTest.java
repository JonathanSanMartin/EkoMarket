package com.ecomarket.ecomarket.services;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.repository.ClienteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombreCompleto("Juan");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("Juan Diaz", clientes.get(0).getNombreCompleto());
    }

    @Test
    public void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombreCompleto("Ana");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.findById(1);

        assertNotNull(encontrado);
        assertEquals("Ana Delgado", encontrado.getNombreCompleto());
    }

    @Test
    public void testFindByEmail() {
        Cliente cliente = new Cliente();
        cliente.setId(2);
        cliente.setEmail("ana@correo.com");

        when(clienteRepository.findByEmail("ana@correo.com")).thenReturn(List.of(cliente));

        Cliente encontrado = clienteService.findByEmail("ana@correo.com");

        assertNotNull(encontrado);
        assertEquals("ana@correo.com", encontrado.getEmail());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        cliente.setNombreCompleto("Pedro Pascal");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente guardado = clienteService.save(cliente);

        assertNotNull(guardado);
        assertEquals("Pedro Pascal", guardado.getNombreCompleto());
    }

    @Test
    public void testDelete() {
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.delete(1);

        verify(clienteRepository, times(1)).deleteById(1);
    }
}
