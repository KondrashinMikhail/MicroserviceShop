package FeedbackCreationService.controllers;

import FeedbackCreationService.entities.Feedback;
import FeedbackCreationService.entities.Product;
import FeedbackCreationService.services.FeedbackService;
import FeedbackCreationService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value="/FeedbackCreationService", method = {RequestMethod.GET, RequestMethod.POST})
public class FeedbackCreationController {

    private final FeedbackService feedbackService;
    private final ProductService productService;
    @Autowired
    private RestTemplate restTemplate;

    public FeedbackCreationController(FeedbackService feedbackService, ProductService productService) {
        this.feedbackService = feedbackService;
        this.productService = productService;
    }

    @GetMapping("/product/{productId}/getFeedbacks")
    public List<Feedback> getProductFeedbacks(@PathVariable Long productId) {
        return feedbackService.findProductsFeedbacks(productId);
    }

    @PostMapping("/createFeedback")
    public String createFeedback(@RequestParam("estimate") Double estimate,
                                 @RequestParam("text") String text,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("productId") Long productId) {
        feedbackService.addFeedback(estimate, text, userId, productId);
        final Product product = productService.findProduct(productId);
        restTemplate.getForObject(
                "http://localhost:92/ProductSynchronizationService/changeProductFeedbackInfo?id=" + productId
                        + "&name=" + product.getName().trim()
                        + "&description=" + product.getDescription().trim()
                        + "&price=" + product.getPrice()
                        + "&warehouseCount=" + product.getWarehouseCount()
                        + "&feedbackCount=" + product.getFeedbackCount()
                        + "&averageEstimate=" + product.getAverageEstimate()
                        + "&organizationId=" + product.getOrganizationId()
                        + "&newEstimate=" + estimate,
                Void.class);
        return "CREATED FEEDBACK AND UPDATED PRODUCT's FEEDBACK INFO";
    }
}
