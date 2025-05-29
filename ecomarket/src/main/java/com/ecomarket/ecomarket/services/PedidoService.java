package com.ecomarket.ecomarket.services;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> findByClienteId(Integer clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
