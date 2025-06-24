package com.ecomarket.ecomarket.services;

import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Kukis");

        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> productos = productoService.findAll();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Kukis", productos.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Galletas");

        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Producto encontrado = productoService.findById(1);

        assertNotNull(encontrado);
        assertEquals("Galletas", encontrado.getNombre());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto();
        producto.setNombre("Leche vegetal");

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto guardado = productoService.save(producto);

        assertNotNull(guardado);
        assertEquals("Leche vegetal", guardado.getNombre());
    }

    @Test
    public void testDelete() {
        doNothing().when(productoRepository).deleteById(1);

        productoService.delete(1);

        verify(productoRepository, times(1)).deleteById(1);
    }
}