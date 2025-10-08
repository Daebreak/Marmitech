package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // teste de Cliente
class ClienteControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        var cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEmail("maria@exemplo.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua das Flores, 123");
        cliente.setDataCadastro(LocalDate.of(2025, 1, 1));
        clienteRepository.save(cliente);
    }

    @Test
    @DisplayName("Salvando um Cliente e retornando sucesso")
    void teste() {
        var clienteSave = new Cliente();
        clienteSave.setNome("Ana");
        clienteSave.setEmail("ana@exemplo.com");
        clienteSave.setTelefone("11888888888");
        clienteSave.setEndereco("Av. Brasil, 456");
        clienteSave.setDataCadastro(LocalDate.of(2025, 2, 1));

        ResponseEntity<Cliente> response = restTemplate
                .postForEntity("/api/clientes", clienteSave, Cliente.class);
    }
    @Test
    @DisplayName("...")
    void teste02(){

    }

    @Test
    @DisplayName("...")
    void teste03(){

    }

    @Test
    @DisplayName("...")
    void teste04(){

    }

    @Test
    @DisplayName("...")
    void teste05(){

    }
}
