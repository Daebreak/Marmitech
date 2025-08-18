package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        cliente.setDataCadastro( LocalDateTime.now().toString() );

        // Validação para não permitir nomes duplicados
        if (cliente.getNome() != null && !cliente.getNome().isBlank()) {
            List<Cliente> clientesComMesmoNome = clienteRepository.findByNome( cliente.getNome() );
            if (!clientesComMesmoNome.isEmpty()) {
                throw new RuntimeException( "Nome já cadastrado" );
            }
        }
        //Validacao para nao ter mais de um cliente com o mesmo CPF/CNPJ
        if (cliente.getCpfCnpj() != null && !cliente.getCpfCnpj().isBlank()) {
            Optional<Cliente> clienteBD = clienteRepository.findByCpfCnpj( cliente.getCpfCnpj() );
            clienteBD.ifPresent( clienteModel -> {
                throw new RuntimeException( "CPF/CNPJ ja cadastrado" );
            } );
        }
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
        clienteUpdate.setDataCadastro( LocalDateTime.now().toString() );

        if (cliente.getNome() != null && !cliente.getNome().isBlank()) {
            clienteUpdate.setNome( cliente.getNome() );
        }
        if (cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
            clienteUpdate.setEmail( cliente.getEmail() );
        }
        if (cliente.getTelefone() != null && !cliente.getTelefone().isBlank()) {
            clienteUpdate.setTelefone( cliente.getTelefone() );
        }
        if (cliente.getCpfCnpj() != null && !cliente.getCpfCnpj().isBlank()) {
            clienteUpdate.setCpfCnpj( cliente.getCpfCnpj() );
        }
        if (cliente.getEndereco() != null && !cliente.getEndereco().isBlank()) {
            clienteUpdate.setEndereco( cliente.getEndereco() );
        }
        return clienteRepository.save( clienteUpdate );

    }

    public List<Cliente> findByNome(String nome) {
        return clienteRepository.getByNome( nome );
    }

    public Cliente findByCpfCnpj(String cpf_cnpj) {
        return clienteRepository.getByCpfCnpj( cpf_cnpj );
    }
}
