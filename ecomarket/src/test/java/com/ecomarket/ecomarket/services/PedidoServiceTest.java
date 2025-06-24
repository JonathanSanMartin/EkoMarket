package com.ecomarket.ecomarket.services;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setClienteId(10);

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.findAll();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(10, pedidos.get(0).getClienteId());
    }

    @Test
    public void testFindById() {
        Pedido pedido = new Pedido();
        pedido.setId(1);

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));

        Pedido encontrado = pedidoService.findById(1);

        assertNotNull(encontrado);
        assertEquals(1, encontrado.getId());
    }

    @Test
    public void testFindByClienteId() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setClienteId(20);

        when(pedidoRepository.findByClienteId(20)).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.findByClienteId(20);

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(20, pedidos.get(0).getClienteId());
    }

    @Test
    public void testSave() {
        Pedido pedido = new Pedido();
        pedido.setClienteId(30);

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido guardado = pedidoService.save(pedido);

        assertNotNull(guardado);
        assertEquals(30, guardado.getClienteId());
    }

    @Test
    public void testDelete() {
        doNothing().when(pedidoRepository).deleteById(1);

        pedidoService.delete(1);

        verify(pedidoRepository, times(1)).deleteById(1);
    }
}