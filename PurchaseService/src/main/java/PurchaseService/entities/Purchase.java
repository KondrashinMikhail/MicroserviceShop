package PurchaseService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase", schema = "public")
public class Purchase {
    @Id
    private Long id;
    private Double price;
    private Date date;
    private Long userId;
    private Long productId;

    public Purchase(Double price, Date date, Long userId, Long productId) {
        this.price = price;
        this.date = date;
        this.userId = userId;
        this.productId = productId;
    }
}
