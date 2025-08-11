package Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class pedido_Item {
    @Id
    String pedido_Item;
    int pedido_id;
    int produto_id;
    int quantidade;
    double preco_unitario_pedido;
    Double subTotal;
}

