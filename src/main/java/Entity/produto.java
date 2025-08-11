package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class produto {
    @Id
    String nome;
    String descricao;
    double preco_Unitario;
    int Estoque;
    String Categoria;
}
