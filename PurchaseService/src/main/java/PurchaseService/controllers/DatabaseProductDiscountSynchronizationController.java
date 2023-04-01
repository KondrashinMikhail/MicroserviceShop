package PurchaseService.controllers;

import PurchaseService.services.ProductDiscountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/PurchaseService/ProductDiscountSynchronization", method = {RequestMethod.GET, RequestMethod.POST})
public class DatabaseProductDiscountSynchronizationController {
    private final ProductDiscountService productDiscountService;

    public DatabaseProductDiscountSynchronizationController(ProductDiscountService productDiscountService) {
        this.productDiscountService = productDiscountService;
    }

    @PostMapping("/insert")
    public void insert(@RequestParam("productId") Long productId,
                       @RequestParam("discountId") Long discountId) {
        productDiscountService.addValue(productId, discountId);
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("productId") Long productId,
                       @RequestParam("discountId") Long discountId) {
        productDiscountService.updateValue(id, productId, discountId);
    }
}
