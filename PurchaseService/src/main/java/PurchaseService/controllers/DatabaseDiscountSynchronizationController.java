package PurchaseService.controllers;

import PurchaseService.services.DiscountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/PurchaseService/DiscountSynchronization", method = {RequestMethod.GET, RequestMethod.POST})
public class DatabaseDiscountSynchronizationController {

    private final DiscountService discountService;

    public DatabaseDiscountSynchronizationController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/insert")
    public void insertProduct(@RequestParam("duration") int duration,
                              @RequestParam("amount") Double amount) {
        discountService.addDiscount(duration, amount);
    }

    @PostMapping("/update")
    public void updateProduct(@RequestParam("id") Long id,
                              @RequestParam("duration") int duration,
                              @RequestParam("amount") Double amount) {
        discountService.updateDiscount(id, discountService.findDiscount(id).getCreationDate(), duration, amount);
    }
}
