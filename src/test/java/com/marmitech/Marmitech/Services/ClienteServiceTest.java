package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)

@ActiveProfiles("test")
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    Cliente cliente;

    @BeforeEach
    void setUp() {

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Ane");
        cliente.setEmail("ane@exemplo.com");
        cliente.setTelefone("11888888888");
        cliente.setEndereco("Av. Brasil, 456");
        cliente.setCpfCnpj("11122233344");
        cliente.setDataCadastro(String.valueOf(LocalDate.of(2025, 2, 3)));
        // this.cliente = clienteRepository.save(cliente);
    }

    @Test
    @DisplayName("Testando o método save da ClienteService")
    void cenario01() {
        var novoCliente = new Cliente();
        novoCliente.setNome("Carlos"); // nome diferente
        novoCliente.setEmail("carlos@exemplo.com");
        novoCliente.setTelefone("11999999999");
        novoCliente.setEndereco("Rua X, 123");
        novoCliente.setCpfCnpj("12345678901"); // CPF diferente
        novoCliente.setDataCadastro(String.valueOf((LocalDate.now())));


        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente cliente = invocation.getArgument(0);
            cliente.setId(10);
            return cliente;
        });

        //normal nenhum duplicado
        var resultado = clienteService.save(novoCliente);
        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNome());

        verify(clienteRepository, times(1)).save(any(Cliente.class));

        //nao pode duplicado
        var clienteSemNome = new Cliente();
        clienteSemNome.setNome("Carlos");
        var resultadoSemNome = clienteService.save(clienteSemNome);
        assertNotNull(resultadoSemNome);


        var clienteSemCpf = new Cliente();
        clienteSemCpf.setCpfCnpj(null); // só para não disparar o outro if
        var resultado2 = clienteService.save(clienteSemCpf);
        assertNotNull(resultado2);



    }


    @Test
    @DisplayName("Listar todos os clientes")
    void cenario02() {
        var clienteLista = new Cliente();
        clienteLista.setId(1);
        clienteLista.setNome("Carlos");
        clienteLista.setEmail("carlos@exemplo.com");
        clienteLista.setTelefone("11999999999");
        clienteLista.setEndereco("Rua X, 123");
        clienteLista.setCpfCnpj("123435464");
        clienteLista.setDataCadastro(String.valueOf((LocalDate.now())));
        var clientes2 = new Cliente();
        clientes2.setId(2);
        clientes2.setNome("Ane");
        clientes2.setEmail("ane@exemplo.com");
        clientes2.setTelefone("11888888888");
        clientes2.setEndereco("Av. Brasil, 456");
        clientes2.setCpfCnpj("11122233344");
        clientes2.setDataCadastro(String.valueOf((LocalDate.now())));
        List<Cliente> lista = Arrays.asList(clienteLista, clientes2);

        when(clienteRepository.findAll()).thenReturn(lista);

        var clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Carlos", clientes.get(0).getNome());

    }

    @Test
    @DisplayName("lista de ID")
    void cenario03() {

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        var response = clienteService.findById(1);

        assertNotNull(response);
        assertEquals("Ane", response.getNome());

        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Deletar clientes pelo ID ")
    void cenario04() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        clienteService.delete(1);
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    @DisplayName("Cenário 05 - Teste de atualização com cliente nulo cobertura dos ifs")
    void cenario05() {

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        cliente.setNome("Ane");
        clienteService.update(1, cliente);

        cliente.setNome(null);
        //clienteService.update(null, null);
        cliente.setEmail(null);
        cliente.setTelefone(null);
        cliente.setEndereco(null);
        cliente.setCpfCnpj(null);
        cliente.setDataCadastro(null);
        //clienteService.update(1, cliente);

        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, atLeastOnce()).save(any());
        assertNotNull(cliente);

    }

    @Test
    @DisplayName("cenario 06  Teste de busca de cliente por nomee ")
    void cenario06() {
        when(clienteRepository.getByNome(anyString())).thenReturn(List.of(new Cliente()));

        var resultado = clienteService.findByNome("Carlos");

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("cenario 07 teste de CPF e CNPJ ")
    void cenario07() {
        cliente.setCpfCnpj("123435464");

        when(clienteRepository.getByCpfCnpj("123435464")).thenReturn(cliente);

        Cliente resultado = clienteService.findByCpfCnpj("123435464");

        assertEquals("123435464", resultado.getCpfCnpj());
        verify(clienteRepository).getByCpfCnpj("123435464");
    }
}

