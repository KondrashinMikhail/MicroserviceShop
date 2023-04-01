package PurchaseService.dto;

import PurchaseService.entities.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private Double price;
    private Long userId;
    private Long productId;

    public PurchaseDto(Purchase purchase) {
        this.id = purchase.getId();
        this.price = purchase.getPrice();
        this.userId = purchase.getUserId();
        this.productId = purchase.getProductId();
    }
}
