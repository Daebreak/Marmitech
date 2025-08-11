package Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class pedidos {
    @Id
    int pedido_id;
    int cliente_id;
    String dataPedido;
    Double valorTotal;
    String status;
    String enderecoEntrega;
}
