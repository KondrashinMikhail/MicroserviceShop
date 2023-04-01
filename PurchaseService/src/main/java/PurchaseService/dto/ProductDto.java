package PurchaseService.dto;

import PurchaseService.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
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

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.warehouseCount = product.getWarehouseCount();
        this.feedbackCount = product.getFeedbackCount();
        this.averageEstimate = product.getAverageEstimate();
        this.organizationId = product.getOrganizationId();
    }
}
