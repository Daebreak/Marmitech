package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails{
//private String login;
//private String password;
//private String username;
//private String roles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank(message = "nome não pode ser null ou vazio")
    private String nome;

    @NotNull
    @NotBlank(message = "senha não pode ser null ou vazio")
    private String senha;

    @NotNull
    @NotBlank(message = "email não pode ser null ou vazio")
    private String email;

    @NotNull
    @NotBlank(message = "cargo não pode ser null ou vazio")
    private String cargo;

    private LocalDate data_criacao = LocalDate.now();


    //Um usuario(caixa) pode registrar varios pedidos
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Pedido> pedidos;


    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {

        return this.senha;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.nome;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
