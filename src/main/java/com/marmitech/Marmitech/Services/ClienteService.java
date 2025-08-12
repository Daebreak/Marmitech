package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        cliente.setData_cadastro( LocalDateTime.now() );
        return clienteRepository.save( cliente );
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById( id ).orElseThrow( RuntimeException::new );
    }

    public void delete(Integer id) {
        var delete = findById( id );
        clienteRepository.delete( delete );
    }

    public Cliente update(Integer id, Cliente cliente) {
        Cliente clienteUpdate = findById( id );

        if (cliente.getNome() != null || !cliente.getNome().isBlank()) {
            clienteUpdate.setNome( cliente.getNome() );
        }
        if (cliente.getEmail() != null || !cliente.getEmail().isBlank()) {
            clienteUpdate.setEmail( cliente.getEmail() );
        }
        if (cliente.getTelefone() != null || !cliente.getTelefone().isBlank()) {
            clienteUpdate.setTelefone( cliente.getTelefone() );
        }
        if (cliente.getCpf_cnpj() != null || !cliente.getCpf_cnpj().isBlank()) {
            clienteUpdate.setCpf_cnpj( cliente.getCpf_cnpj() );
        }
        if (cliente.getEndereco() != null || !cliente.getEndereco().isBlank()) {
            clienteUpdate.setCpf_cnpj( cliente.getEndereco() );
        }

        return clienteRepository.save( clienteUpdate );

    }
}
