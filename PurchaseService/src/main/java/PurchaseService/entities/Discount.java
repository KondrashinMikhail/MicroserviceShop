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
@Table(name = "discount", schema = "public")
public class Discount {
    @Id
    private Long id;
    private Date creationDate;
    private int duration;
    private Double amount;

    public Discount(Date creationDate, int duration, Double amount) {
        this.creationDate = creationDate;
        this.duration = duration;
        this.amount = amount;
    }
}
