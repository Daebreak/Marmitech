package com.marmitech.Marmitech.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // ✅ CORRETO
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("01 - POST /save")
    void cenario01() throws Exception {
        Mockito.when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        // transforma o objeto em JSON
        String usuarioJson = objectMapper.writeValueAsString(usuario);

        // executa o POST simulando a requisição HTTP
        mockMvc.perform(post("/api/usuario/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Marmitech"))
                        .andExpect(jsonPath("$.email").value("marmitech@gmail.com"))
                        .andExpect(jsonPath("$.cargo").value("Caixa"))
                        .andExpect(jsonPath("$.data_criacao").value(LocalDate.now().toString()));


    }
}
