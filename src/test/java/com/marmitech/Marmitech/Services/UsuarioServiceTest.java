package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Controller.UsuarioController;
//import com.marmitech.Marmitech.Controller.UsuarioControllerTest;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    private UsuarioController usuarioController;

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
        novoUsuario.setData_criacao(LocalDate.now());


        var responsee = usuarioService.save(novoUsuario);
        ResponseEntity<Usuario> response = restTemplate
                .postForEntity("/api/usuario/save"
                        , novoUsuario, Usuario.class);

        Assertions.assertEquals(HttpStatus.CREATED
                , response.getStatusCode());

       Assertions.assertNotNull(HttpStatus.BAD_REQUEST);
        //var response = usuarioService.save(novoUsuario);
     // String retorno= this.usuarioService.save();
       /*assertNotNull(response.getId());
        assertEquals("Ana", response.getNome());*/
    }
    @Test
    void usuarioInvalidoSave() {
//        var novoUsuario = new Usuario();
//        novoUsuario.setNome("Gabi");
//        novoUsuario.setEmail("gabi@gabi.com");
//        novoUsuario.setSenha("123456");
//        novoUsuario.setCargo("Caixa");
//        novoUsuario.setData_criacao(LocalDate.now());
//
//        var responsee = usuarioService.save(novoUsuario);
//      assertThrows(Exception.class, () -> {
//          this.usuarioService.save(novoUsuario);
//          //ResponseEntity<Usuario>response = usuarioService.save(novoUsuario);
//      });
//        Assertions.assertTrue(responsee.isPresent());
//        Assertions.assertNotNull("Gabi",responsee,getNome());
    }
   @Test
   void cenario02() {
       var usuarios = usuarioService.findAll();
     //  usuarios

       Assertions.assertNotNull("Ana");
       Assertions.assertTrue(usuarios.size() > 0);

   }
   @Test
    void cenario03() {
       var response =usuarioRepository.findById(usuario.getId());

       Assertions.assertTrue(response.isPresent());
       Assertions.assertNotNull("Gabi",response.get().getNome());
   }
}
