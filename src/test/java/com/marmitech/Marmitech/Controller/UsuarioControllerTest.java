package com.marmitech.Marmitech.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@WebMvcTest(UsuarioController.class)
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    ObjectMapper objectMapper;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Marmitech");
        usuario.setSenha("123456");
        usuario.setEmail("marmitech@gmail.com");
        usuario.setCargo("Caixa");
        usuario.setData_criacao(LocalDate.now());
    }

    @Test
    @DisplayName("01 - POST ")
    void cenario01() throws Exception {

        usuario.setNome("Marmitech");
        var result = usuarioService.save( usuario );
        when(usuarioService.save(usuario),ResponseEntity<usuario>( result, HttpStatus.CREATED );
    }
}
