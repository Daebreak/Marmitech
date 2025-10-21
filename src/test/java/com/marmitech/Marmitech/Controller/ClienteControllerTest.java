package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import com.marmitech.Marmitech.Services.ClienteService;
import com.marmitech.Marmitech.Services.ClienteServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // teste de Cliente
class ClienteControllerTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    Cliente cliente;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        var cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEmail("maria@exemplo.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua das Flores, 123");
        cliente.setCpfCnpj("111222333000");
        cliente.setDataCadastro(String.valueOf(LocalDate.of(2025, 1, 1)));
        this.cliente = clienteRepository.save(cliente);

    }

    @Test
    @DisplayName("POST/ save = Criar cliente")
    void teste() {
        var clienteSave = new Cliente();
        clienteSave.setNome("Ana");
        clienteSave.setEmail("ana@exemplo.com");
        clienteSave.setTelefone("11888888888");
        clienteSave.setEndereco("Av. Brasil, 456");
        clienteSave.setCpfCnpj("111222333448");
        clienteSave.setDataCadastro(String.valueOf(LocalDate.now()));

        ResponseEntity<Cliente> response = restTemplate
                .postForEntity("/api/cliente/save", clienteSave, Cliente.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ana", response.getBody().getNome());


    }
    @Test
    @DisplayName("GET : Lista do Clientes")
    void teste02(){
        ResponseEntity<Cliente[]> response = testRestTemplate.exchange(
                "/api/cliente/findAll",
                HttpMethod.GET,
                null,
                Cliente[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
