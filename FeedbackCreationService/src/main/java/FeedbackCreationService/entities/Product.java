package FeedbackCreationService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "public")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int warehouseCount;
    //discounts;
    //words
    //characteristics table
    private int feedbackCount;
    private Double averageEstimate;
    private Long organizationId;

    public Product(String name, String description, Double price, int warehouseCount, int feedbackCount, Double averageEstimate, Long organizationId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.warehouseCount = warehouseCount;
        this.feedbackCount = feedbackCount;
        this.averageEstimate = averageEstimate;
        this.organizationId = organizationId;
    }
}
