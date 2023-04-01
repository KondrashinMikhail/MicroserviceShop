package PurchaseSynchronizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/PurchaseSynchronizationService", method = {RequestMethod.GET, RequestMethod.POST})
public class PurchaseSynchronizationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/insert")
    public void insert(@RequestParam("price") Double price,
                       @RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        restTemplate.getForObject("http://localhost:88/PurchaseService/PurchaseSynchronization/insert?price=" + price
                + "&userId=" + userId
                + "&productId=" + productId,
                Void.class);
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("price") Double price,
                       @RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        restTemplate.getForObject("http://localhost:88/PurchaseService/PurchaseSynchronization/update?id=" + id
                        + "&price=" + price
                        + "&userId=" + userId
                        + "&productId=" + productId,
                Void.class);
    }
}
