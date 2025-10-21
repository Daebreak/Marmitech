package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import com.marmitech.Marmitech.Services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(controllers = UsuarioController.class)
@ActiveProfiles("test") // teste do Usuario
class UsuarioControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Marmitech");
        usuario.setSenha("123456");
        usuario.setEmail("marmitech@gmail.com");
        usuario.setCargo("Caixa");


    }
  @Test
    void cenarioPost(){
       Usuario usuario = new Usuario();
      usuario.setId(1);
       usuario.setNome("Marmitech");
       usuario.setData_criacao((LocalDate.now()));
       usuario.setSenha("123456");
       usuario.setEmail("marmitech@gmail.com");
       usuario.setCargo("Caixa");

      var response = usuarioRepository.save(usuario);



      Assertions.assertNotNull(response);
        Assertions.assertEquals(usuario.getNome()
               , response.getNome());
      Assertions.assertEquals(response, HttpStatus.CREATED);
    }
    @Test
    void cenarioGet(){
 usuario.setNome("Marmitech");
 usuario.setSenha("123456");
 usuario.setEmail("marmitech@gmail.com");
 usuario.setCargo("Caixa");
 usuario.setData_criacao((LocalDate.now()));
  var response = usuarioRepository.findAll();

  Assertions.assertEquals(response,usuario);

    }
}
