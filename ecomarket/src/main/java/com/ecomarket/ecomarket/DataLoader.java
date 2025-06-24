package com.ecomarket.ecomarket;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.repository.ClienteRepository;
import com.ecomarket.ecomarket.repository.PedidoRepository;
import com.ecomarket.ecomarket.repository.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear productos
        for (int i = 1; i <= 5; i++) {
            Producto producto = new Producto();
            producto.setCodigoProducto("P-" + i);
            producto.setNombre(faker.food().ingredient());
            producto.setCategoriaEco(faker.options().option("Orgánico", "Reciclado", "Vegano"));
            producto.setPrecio(faker.number().randomDouble(2, 1000, 10000));
            producto.setStock(faker.number().numberBetween(10, 100));
            producto.setProveedor(faker.company().name());
            productoRepository.save(producto);
        }

        // Crear clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setEmail(faker.internet().emailAddress());
            cliente.setNombreCompleto(faker.name().fullName());
            cliente.setDireccion(faker.address().fullAddress());
            cliente.setFechaNacimiento(faker.date().birthday(18,65));
            cliente.setTelefono(faker.phoneNumber().cellPhone());
            clienteRepository.save(cliente);
        }

        // Crear pedidos
        List<Cliente> clientes = clienteRepository.findAll();
        List<Producto> productos = productoRepository.findAll();

        for (int i = 1; i <= 15; i++) {
            Pedido pedido = new Pedido();
            pedido.setProducto(productos.get(random.nextInt(productos.size())));
            pedido.setClienteId(clientes.get(random.nextInt(clientes.size())).getId());
            pedido.setFechaPedido(new Date());
            pedido.setCantidad(faker.number().numberBetween(1, 5));
            pedido.setEstado(faker.options().option("Pendiente", "Enviado", "Entregado"));
            pedidoRepository.save(pedido);
        }
    }
}

