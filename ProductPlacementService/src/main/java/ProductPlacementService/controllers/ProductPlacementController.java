package ProductPlacementService.controllers;

import ProductPlacementService.entities.Product;
import ProductPlacementService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value="/ProductPlacementService", method = {RequestMethod.GET, RequestMethod.POST})
public class ProductPlacementController {

    private final ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    public ProductPlacementController(ProductService productService) {
        this.productService = productService;
    }
//http://localhost:84/FeedbackCreationService/createFeedback?estimate=5&text=example text&userId=1&productId=1

//http://localhost:80/ProductPlacementService/placeProduct?name=name of product&description=description of product&price=1000&warehouseCount=10&organizationId=1
    @PostMapping("/placeProduct")
    public String placeProduct(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("price") Double price,
                               @RequestParam("warehouseCount") int warehouseCount,
                               @RequestParam("organizationId") Long organizationId) {
        restTemplate.getForObject(
                "http://localhost:92/ProductSynchronizationService/insert?name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&organizationId=" + organizationId,
                Void.class);
        return "PRODUCT PLACED";
    }

    @GetMapping("/organization/{organizationId}/products")
    public List<Product> findOrganizationProducts(@PathVariable("organizationId") Long organizationId) {
        return productService.findOrganizationProducts(organizationId);
    }
}
