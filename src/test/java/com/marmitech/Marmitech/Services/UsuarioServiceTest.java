package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
   // private Usuario usuarioUpdate;
    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        //usuarioUpdate = new Usuario();
        usuario.setId(1);
        usuario.setNome("Gabi");
        usuario.setEmail("gabi@gabi.com");
        usuario.setSenha("123456");
        usuario.setCargo("Caixa");
        usuario.setData_criacao(LocalDate.of(2025, 1, 1));
       // usuarioUpdate.setEmail(null); teste de update para ver se ia dar verde( IFs)
    }

    //  Testar o método save
    @Test
    @DisplayName("Cenário 01 - Testar método save da UsuarioService")
    void cenario01() {
        var novoUsuario = new Usuario();
        novoUsuario.setNome("Ana");
        novoUsuario.setEmail("ana@email.com");
        novoUsuario.setSenha("abcd");
        novoUsuario.setCargo("Gerente");
        novoUsuario.setData_criacao(LocalDate.now());

        // Simula o comportamento do repository
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setId(10); // simula ID gerado pelo banco
            return u;
        });

        var usuarioSalvo = usuarioService.save(novoUsuario);

        assertNotNull(usuarioSalvo);
        assertEquals("Ana", usuarioSalvo.getNome());
        assertTrue(usuarioSalvo.getId() > 0);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    //  Testar findAll
    @Test
    @DisplayName("Cenário 02 - Listar todos os usuários")
    void cenario02() {
        var usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNome("Ana");
        usuario1.setEmail("ana@email.com");
        usuario1.setSenha("abcd");
        usuario1.setCargo("Gerente");
        usuario1.setData_criacao(LocalDate.now());
        var usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("Gabi");
        usuario2.setEmail("gabi@gabi.com");
        usuario2.setSenha("123456");
        usuario2.setCargo("Caixa");
        usuario2.setData_criacao(LocalDate.now());
        List<Usuario> lista = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(lista);

        var usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        assertEquals("Ana", usuarios.get(0).getNome());

        verify(usuarioRepository, times(1)).findAll();
    }

    // - Testar findById
    @Test
    @DisplayName("Cenário 03 - Buscar usuário por ID")
    void cenario03() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        var resultado = usuarioService.findById(1);

        assertNotNull(resultado);
        assertEquals("Gabi", resultado.getNome());

        verify(usuarioRepository, times(1)).findById(1);
    }

    //  Testar delete
    @Test
    @DisplayName("Cenário 04 - Deletar usuário pelo ID")
    void cenario04() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        usuarioService.delete(1);

        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    @DisplayName("Cenario 05 - ")
    void cenario05() {


        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);

        //usuarioService.update(1, usuario);


        usuario.setNome(null);
        usuario.setSenha(null);
        usuario.setCargo(null);
        usuario.setEmail(null);
        usuario.setData_criacao(null);
        usuarioService.update(1, usuario);



        verify(usuarioRepository, times(1)).findById(1);

        verify(usuarioRepository,atLeastOnce()).save(any());
        assertNotNull(usuario);

    }
@Test
@DisplayName("cENARIO 06 De login e senha ")
    void cenario06() {
//  when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
//
//   String nome;
//   String senha;
//    Optional<Usuario>usuarioOPTl  = usuarioRepository.findByNomeAndSenha( nome, senha );
//
   }
 @Test
    @DisplayName("Cenario 07 de lista de usuarios do Cargos")
    void cenario07() {
     when(usuarioRepository.getByCargo(anyString())).thenReturn(List.of(new Usuario()));

     var resultado = usuarioService.findByCargo("Caixa");

     assertFalse(resultado.isEmpty());

 }
 @Test
    @DisplayName("Cenario 08 teste de Nome")
    void cenario08() {
   usuario.setNome("Ana");
  when(usuarioRepository.findByNome(anyString())).thenReturn(List.of(usuario));
   var resultado = usuarioService.findByNome("Ana");

  assertFalse(resultado.isEmpty());
     assertEquals("Ana", resultado.get(0).getNome());
 }
}
