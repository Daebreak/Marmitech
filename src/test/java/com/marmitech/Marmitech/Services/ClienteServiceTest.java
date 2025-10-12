package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
      private TestRestTemplate restTemplate;
    //@MockBean
    @Autowired
    private ClienteRepository clienteRepository;

    Cliente cliente;
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        cliente = new Cliente();
        cliente.setNome("Ane");
        cliente.setEmail("ane@exemplo.com");
        cliente.setTelefone("11888888888");
        cliente.setEndereco("Av. Brasil, 456");
       cliente.setCpfCnpj("11122233344");
        cliente.setDataCadastro(String.valueOf(LocalDate.of(2025,2,3)));
     this.cliente = clienteRepository.save(cliente);
    }
    @Test
    @DisplayName("Testando o método save da ClienteService")
    void  cenario01() {
        var novoCliente = new Cliente();
        novoCliente.setNome("Carlos"); // nome diferente
        novoCliente.setEmail("carlos@exemplo.com");
        novoCliente.setTelefone("11999999999");
        novoCliente.setEndereco("Rua X, 123");
        novoCliente.setCpfCnpj("12345678901"); // CPF diferente
        novoCliente.setDataCadastro(String.valueOf(LocalDate.of(2025, 10, 10)));

        //var response = clienteService.save(novoCliente);

        //var respon = clienteService.save(novoCliente);
        ResponseEntity<Cliente> response = restTemplate
                .postForEntity("/api/cliente/save"
                        , novoCliente, Cliente.class);

        Assertions.assertEquals(HttpStatus.CREATED
                , response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Carlos", response.getBody().getNome());


  /* assertNotNull(response.getId());
   assertEquals("Ane", response.getNome());*/
    }

}