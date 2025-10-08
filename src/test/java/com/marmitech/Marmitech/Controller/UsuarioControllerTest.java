package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // teste do Usuario
class UsuarioControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
      UsuarioRepository usuarioRepository;

    @BeforeEach
void setUp() {
        usuarioRepository.deleteAll();
        var usuario = new Usuario();
        usuario.setNome("Gabi");
        usuario.setEmail("gabi@gabi.com");
        usuario.setSenha("123456");
        usuario.setCargo("Caixa");
        usuario.setData_criacao(LocalDate.of(2025, 1, 1));
        usuarioRepository.save(usuario);
    }
 @Test
    @DisplayName("Salvando um Usuario e retornando sucesso")
    void teste01() {
        var usuarioSave = new Usuario();
        usuarioSave.setNome("Gabi");
        usuarioSave.setEmail("gabi@gabi.com");
        usuarioSave.setSenha("123456");
        usuarioSave.setCargo("Caixa");
        usuarioSave.setData_criacao(LocalDate.of(2025, 1, 1));
     ResponseEntity<Usuario> response = restTemplate
             .postForEntity("/api/usuario"
                     , usuarioSave, Usuario.class);
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
