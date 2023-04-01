package DiscountsAnnouncementService.dto;

import DiscountsAnnouncementService.entities.ProductDiscount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscountDto {
    private Long id;
    private Long productId;
    private Long discountId;

    public ProductDiscountDto(ProductDiscount productDiscount) {
        this.id = productDiscount.getId();
        this.productId = productDiscount.getProductId();
        this.discountId = productDiscount.getDiscountId();
    }
}
