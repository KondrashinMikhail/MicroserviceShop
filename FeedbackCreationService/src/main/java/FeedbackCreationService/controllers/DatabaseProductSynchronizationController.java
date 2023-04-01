package FeedbackCreationService.controllers;

import FeedbackCreationService.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/FeedbackCreationService/ProductSynchronization", method = {RequestMethod.GET, RequestMethod.POST})
public class DatabaseProductSynchronizationController {

    private final ProductService productService;

    public DatabaseProductSynchronizationController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert")
    public void insertProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price,
                              @RequestParam("warehouseCount") int warehouseCount,
                              @RequestParam("organizationId") Long organizationId) {
        productService.addProduct(name, description, price, warehouseCount, organizationId);
    }

    @PostMapping("/update")
    public void updateProduct(@RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price,
                              @RequestParam("warehouseCount") int warehouseCount,
                              @RequestParam("feedbackCount") int feedbackCount,
                              @RequestParam("averageEstimate") Double averageEstimate,
                              @RequestParam("organizationId") Long organizationId) {
        productService.updateProduct(id, name, description, price, warehouseCount, feedbackCount, averageEstimate, organizationId);
    }

}
