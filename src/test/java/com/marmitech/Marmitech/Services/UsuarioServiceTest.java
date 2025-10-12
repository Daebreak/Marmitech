package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        var usuario = new Usuario();
        usuario.setNome("Gabi");
        usuario.setEmail("gabi@gabi.com");
        usuario.setSenha("123456");
        usuario.setCargo("Caixa");
        usuario.setData_criacao(LocalDate.of(2025, 1, 1));

        this.usuario = usuarioRepository.save(usuario);
    }

    @Test
    @DisplayName("Testando o método save da UsuarioService")
    void cenario01() {
        var novoUsuario = new Usuario();
        novoUsuario.setNome("Ana");
        novoUsuario.setEmail("ana@email.com");
        novoUsuario.setSenha("abcd");
        novoUsuario.setCargo("Gerente");
        novoUsuario.setData_criacao(LocalDate.of(2025, 1, 1));


        var responsee = usuarioService.save(novoUsuario);
        ResponseEntity<Usuario> response = restTemplate
                .postForEntity("/api/usuario/save"
                        , novoUsuario, Usuario.class);

        Assertions.assertEquals(HttpStatus.CREATED
                , response.getStatusCode());


        //var response = usuarioService.save(novoUsuario);
     // String retorno= this.usuarioService.save();
       /*assertNotNull(response.getId());
        assertEquals("Ana", response.getNome());*/
    }
   /* @Test
    void cenario02() {
        var usuario = new Usuario();
        usuario.setNome(null);

        assertThrows(Exception.class, () -> usuarioService.save(usuario));
    }*/
}
