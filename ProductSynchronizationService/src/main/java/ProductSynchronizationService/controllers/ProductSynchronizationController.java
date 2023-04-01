package ProductSynchronizationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/ProductSynchronizationService", method = {RequestMethod.GET, RequestMethod.POST})
public class ProductSynchronizationController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("price") Double price,
                       @RequestParam("feedbackCount") int feedbackCount,
                       @RequestParam("averageEstimate") Double averageEstimate,
                       @RequestParam("warehouseCount") int warehouseCount,
                       @RequestParam("organizationId") Long organizationId) {
        restTemplate.getForObject(
                "http://localhost:8080/PurchaseService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + feedbackCount
                        + "&averageEstimate=" + averageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:84/FeedbackCreationService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + feedbackCount
                        + "&averageEstimate=" + averageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:83/DiscountsAnnouncementService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + feedbackCount
                        + "&averageEstimate=" + averageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:80/ProductPlacementService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + feedbackCount
                        + "&averageEstimate=" + averageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);
    }

    @PostMapping("/changeProductFeedbackInfo")
    public void changeFeedbackInfo(@RequestParam("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("price") Double price,
                                   @RequestParam("warehouseCount") int warehouseCount,
                                   @RequestParam("feedbackCount") int feedbackCount,
                                   @RequestParam("averageEstimate") Double averageEstimate,
                                   @RequestParam("organizationId") Long organizationId,
                                   @RequestParam("newEstimate") Double newEstimate) {

        double newAverageEstimate = ((feedbackCount * averageEstimate) + newEstimate) / (feedbackCount + 1);
        int newFeedbackCount = feedbackCount + 1;

        restTemplate.getForObject(
                "http://localhost:8080/PurchaseService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + newFeedbackCount
                        + "&averageEstimate=" + newAverageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:84/FeedbackCreationService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + newFeedbackCount
                        + "&averageEstimate=" + newAverageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:83/DiscountsAnnouncementService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + newFeedbackCount
                        + "&averageEstimate=" + newAverageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:80/ProductPlacementService/ProductSynchronization/update?id=" + id
                        + "&name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&feedbackCount=" + newFeedbackCount
                        + "&averageEstimate=" + newAverageEstimate
                        + "&organizationId=" + organizationId,
                Void.class);
    }

    @PostMapping("/insert")
    public void insert(@RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("price") Double price,
                       @RequestParam("warehouseCount") int warehouseCount,
                       @RequestParam("organizationId") Long organizationId) {
        restTemplate.getForObject(
                "http://localhost:8080/PurchaseService/ProductSynchronization/insert?name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:84/FeedbackCreationService/ProductSynchronization/insert?name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:83/DiscountsAnnouncementService/ProductSynchronization/insert?name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&organizationId=" + organizationId,
                Void.class);

        restTemplate.getForObject(
                "http://localhost:80/ProductPlacementService/ProductSynchronization/insert?name=" + name
                        + "&description=" + description
                        + "&price=" + price
                        + "&warehouseCount=" + warehouseCount
                        + "&organizationId=" + organizationId,
                Void.class);
    }
}
