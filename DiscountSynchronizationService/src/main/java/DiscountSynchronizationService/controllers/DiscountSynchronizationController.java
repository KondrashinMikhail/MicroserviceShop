package DiscountSynchronizationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/DiscountSynchronizationService", method = {RequestMethod.GET, RequestMethod.POST})
public class DiscountSynchronizationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/insert")
    public void insert(@RequestParam("duration") int duration,
                       @RequestParam("amount") Double amount) {
        restTemplate.getForObject("http://localhost:83/DiscountsAnnouncementService/DiscountSynchronization/insert?duration=" + duration
                + "&amount=" + amount,
                Void.class);

        restTemplate.getForObject("http://localhost:8080/PurchaseService/DiscountSynchronization/insert?duration=" + duration
                        + "&amount=" + amount,
                Void.class);
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("duration") int duration,
                       @RequestParam("amount") Double amount) {
        restTemplate.getForObject("http://localhost:83/DiscountsAnnouncementService/DiscountSynchronization/update?id="+ id
                        + "&duration=" + duration
                        + "&amount=" + amount,
                Void.class);

        restTemplate.getForObject("http://localhost:8080/PurchaseService/DiscountSynchronization/update?id="+ id
                        + "&duration=" + duration
                        + "&amount=" + amount,
                Void.class);
    }

    @PostMapping("/attachProduct/{productId}/toDiscount/{discountId}")
    public void attachProductToDiscount(@PathVariable("productId") Long productId,
                                        @PathVariable("discountId") Long discountId) {
        restTemplate.getForObject("http://localhost:83/DiscountsAnnouncementService/ProductDiscountSynchronization/insert?productId=" + productId
                + "&discountId=" + discountId,
                Void.class);

        restTemplate.getForObject("http://localhost:8080/PurchaseService/ProductDiscountSynchronization/insert?productId=" + productId
                        + "&discountId=" + discountId,
                Void.class);
    }
}
