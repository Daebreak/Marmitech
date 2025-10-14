package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import com.marmitech.Marmitech.Services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // teste do Usuario
class UsuarioControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
      UsuarioRepository usuarioRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UsuarioService usuarioService;

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
        usuarioSave.setData_criacao(LocalDate.now());
     ResponseEntity<Usuario> response = restTemplate
             .postForEntity("/api/usuario/save"
                     , usuarioSave, Usuario.class);

     assertEquals(HttpStatus.CREATED, response.getStatusCode());
     assertNotNull(response.getBody());
     assertEquals("Ana", response.getBody().getNome());
     assertEquals("ana@gabi.com", response.getBody().getEmail());
     assertEquals("An4_007", response.getBody().getSenha());
     assertEquals("Caixa", response.getBody().getCargo());
     assertEquals(LocalDate.now(), response.getBody().getData_criacao());


     //assertTrue(response.getBody().startsWith("Algo deu errado!"));
    // assertEquals(LocalDate.of(2025, 1, 1), response.getBody().getData_criacao());

    }
@Test
@DisplayName("POST : /api/usuario/save  BAD_REQUEST ao salvar usuário inválido")

void testeUsuarioInalido(){
        var usuarioSave = new Usuario();
        usuarioSave.setNome("");
       // usuarioSave.setEmail("ana@gabi.com"); sem emial
    // usuarioSave.setSenha("An4_007"); sem senhs
        usuarioSave.setCargo("Caixa");
        usuarioSave.setData_criacao(LocalDate.now());

   // Mockito.when(usuarioService.save(Mockito.any())).thenReturn(usuarioSave);

    ResponseEntity<Usuario> response = restTemplate
            .postForEntity("/api/usuario/save"
                    , usuarioSave, Usuario.class);

    assertThrows(Exception.class, () -> {
        int retorno = this.usuarioService.save(usuarioSave).getId();
    });


}
        @Test
    @DisplayName("Get: Listar todos os usuarios")
    void teste02(){

           var usuarioGet = new Usuario();
           usuarioGet.setNome("Ana");
           usuarioGet.setEmail("ana@gabi.com");
           usuarioGet.setSenha("An4_007");
           usuarioGet.setCargo("Caixa");
           usuarioGet.setData_criacao(LocalDate.now());
           // usuarioService.save(usuarioGet);


            usuarioRepository.findAll();
        ResponseEntity<Usuario[]> response = testRestTemplate.exchange(
                    "/api/usuario/findAll",
                    HttpMethod.GET,
                    null,
                    Usuario[].class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody().length > 0);
    }

    @Test
    @DisplayName("Get :  Buscar usuário por ID")
    void teste03(){
        var usuarioId = new Usuario();
        usuarioId.setNome("Ana");
        usuarioId.setEmail("ana@gabi.com");
        usuarioId.setSenha("An4_007");
        usuarioId.setCargo("Caixa");
        usuarioId.setData_criacao(LocalDate.now());

        ResponseEntity<Usuario> postResponse = testRestTemplate
                .postForEntity("/api/usuario/save", usuarioId, Usuario.class);

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());
        Integer idCriado = postResponse.getBody().getId();


        ResponseEntity<Usuario> response = testRestTemplate.exchange(
                "/api/usuario/findById/{id}",HttpMethod.GET,null, Usuario.class,idCriado
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNotNull(response.getBody());
      assertEquals("Ana", response.getBody().getNome());
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
