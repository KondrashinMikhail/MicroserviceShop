package PurchaseService.controllers;

import PurchaseService.services.PurchaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/PurchaseService/PurchaseSynchronization", method = {RequestMethod.GET, RequestMethod.POST})
public class DatabasePurchaseSynchronizationController {

    private final PurchaseService purchaseService;

    public DatabasePurchaseSynchronizationController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/insert")
    public void insert(@RequestParam("price") Double price,
                       @RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        purchaseService.addPurchase(price, productId, userId);
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("price") Double price,
                       @RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        purchaseService.updatePurchase(id, price, userId, productId);
    }
}
