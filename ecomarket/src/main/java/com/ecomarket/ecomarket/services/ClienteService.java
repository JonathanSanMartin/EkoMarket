package com.ecomarket.ecomarket.services;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Nuevo método para buscar por email
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email).stream().findFirst().orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}