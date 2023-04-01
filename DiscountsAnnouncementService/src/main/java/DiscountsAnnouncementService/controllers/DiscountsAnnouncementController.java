package DiscountsAnnouncementService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/DiscountsAnnouncementService", method = {RequestMethod.GET, RequestMethod.POST})
public class DiscountsAnnouncementController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/createDiscount")
    public String createDiscount(@RequestParam("duration") int duration,
                                 @RequestParam("amount") Double amount) {
        restTemplate.getForObject("http://localhost:93/DiscountSynchronizationService/insert?duration=" + duration
                + "&amount=" + amount,
                Void.class);
        return "DISCOUNT CREATED";
    }

    @PostMapping("/attachProduct/{productId}/toDiscount/{discountId}")
    public String attachProductToDiscount(@PathVariable("productId") Long productId,
                                          @PathVariable("discountId") Long discountId) {
        restTemplate.getForObject("http://localhost:8093/DiscountSynchronizationService/attachProduct/" + productId + "/toDiscount/" + discountId,
                Void.class);
        return "PRODUCT ATTACHED TO DISCOUNT";
    }
}
