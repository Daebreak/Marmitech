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
    @DisplayName("Post: criar novo usuario")
    void teste01() {
        var usuarioSave = new Usuario();
        usuarioSave.setNome("Ana");
        usuarioSave.setEmail("ana@gabi.com");
        usuarioSave.setSenha("An4_007");
        usuarioSave.setCargo("Caixa");
        usuarioSave.setData_criacao(LocalDate.of(2025, 1, 1));
     ResponseEntity<Usuario> response = restTemplate
             .postForEntity("/api/usuario/save"
                     , usuarioSave, Usuario.class);

     assertEquals(HttpStatus.CREATED, response.getStatusCode());
     assertNotNull(response.getBody());
     assertEquals("Ana", response.getBody().getNome());
 }
  @Test
    @DisplayName("Get: Listar todos os usuarios")
    void teste02(){

    }

    @Test
    @DisplayName("Get :  Buscar usuário por ID")
    void teste03(){

    }

    @Test
    @DisplayName("Put: atualizada")
    void teste04(){

    }

    @Test
    @DisplayName("Delete: Excluir usuario por ID")
    void teste05(){

    }
    @Test
    @DisplayName("POST: Login")
    void teste06(){

    }

    @Test
    @DisplayName("GET /findByCargo/{cargo}  Buscar usuário por cargo")
    void teste07(){

    }
    @Test
    @DisplayName("GET /findByNome/{nome} Buscar usuário por nome")
    void teste08() {

    }
}
