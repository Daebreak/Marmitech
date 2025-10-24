package com.marmitech.Marmitech.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    //@Column(unique = true)
    public String nome;
    public String descricao;
//Uma categoria 1 para  produtos
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Produto> produtos;
}