package DiscountsAnnouncementService.entities;

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
@Table(name = "product_discount", schema = "public")
public class ProductDiscount {
    @Id
    private Long id;
    private Long productId;
    private Long discountId;

    public ProductDiscount(Long productId, Long discountId) {
        this.productId = productId;
        this.discountId = discountId;
    }
}
