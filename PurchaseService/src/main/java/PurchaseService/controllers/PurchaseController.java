package PurchaseService.controllers;

import PurchaseService.entities.Product;
import PurchaseService.entities.ProductDiscount;
import PurchaseService.services.DiscountService;
import PurchaseService.services.ProductDiscountService;
import PurchaseService.services.ProductService;
import PurchaseService.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/PurchaseService", method = {RequestMethod.GET, RequestMethod.POST})
public class PurchaseController {

    @Autowired
    private RestTemplate restTemplate;

    private final ProductService productService;
    private final ProductDiscountService pdService;
    private final DiscountService discountService;
    private final PurchaseService purchaseService;

    public PurchaseController(ProductService productService, ProductDiscountService pdService, DiscountService discountService, PurchaseService purchaseService) {
        this.productService = productService;
        this.pdService = pdService;
        this.discountService = discountService;
        this.purchaseService = purchaseService;
    }


    @PostMapping("/purchaseProduct")
    public String purchaseProduct(@RequestParam("productId") Long productId,
                                  @RequestParam("userId") Long userId) {

        double price;

        Product product = productService.findProduct(productId);
        List<ProductDiscount> pdList = pdService.findByProductId(productId);

        price = product.getPrice();

        for (ProductDiscount pd : pdList)
            if ((discountService.findDiscount(pd.getDiscountId()).getCreationDate().getTime() + discountService.findDiscount(pd.getDiscountId()).getDuration() * 86400000L) < new Date().getTime())
                price =  product.getPrice() * (discountService.findDiscount(pd.getDiscountId()).getAmount() / 100);


//        restTemplate.getForObject("http://localhost:92/PurchaseSynchronizationService/insert?price=" + price
//                        + "&userId=" + userId
//                        + "&productId=" + productId,
//                Void.class);

        purchaseService.addPurchase(price, productId, userId);

        restTemplate.getForObject("http://localhost:82/BalanceReplenishmentService/subtract?id=" + userId
                        + "&subtractionSum=" + price,
                String.class);

        Long organizationFounderId = restTemplate.getForObject(
                "http://localhost:86/OrganizationCreationService/findOrganizationFounder?organizationId=" + product.getOrganizationId(),
                Long.class);

        restTemplate.getForObject("http://localhost:82/BalanceReplenishmentService/replenish?id=" + organizationFounderId
                        + "&additionalSum=" + price * 0.05,
                String.class);

        restTemplate.getForObject(
                "http://localhost:92/ProductSynchronizationService/update?id="+ product.getId() + "&name=" + product.getName()
                        + "&description=" + product.getDescription()
                        + "&price=" + product.getPrice()
                        + "&warehouseCount=" + (product.getWarehouseCount() - 1)
                        + "&feedbackCount=" + product.getFeedbackCount()
                        + "&averageEstimate=" + product.getAverageEstimate()
                        + "&organizationId=" + product.getOrganizationId(),
        Void.class);

        return "PRODUCT PURCHASED";
    }
}
