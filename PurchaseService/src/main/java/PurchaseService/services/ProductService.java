package PurchaseService.services;

import PurchaseService.entities.Product;
import PurchaseService.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Transactional
    public void addProduct(String name, String description, Double price, int warehouseCount, Long organizationId) {
        Long id = (long) (productRepository.findAll().size() + 1);
        final Product product = new Product(id, name, description, price, warehouseCount, 0, 0.0, organizationId);
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long id, String name, String description, Double price, int warehouseCount, int feedbackCount, Double averageEstimate, Long organizationId) {
        final Product product = findProduct(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWarehouseCount(warehouseCount);
        product.setFeedbackCount(feedbackCount);
        product.setAverageEstimate(averageEstimate);
        product.setOrganizationId(organizationId);
        productRepository.save(product);

    }

    @Transactional
    public void deleteProduct(Long id) {
        final Product product = findProduct(id);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Product findProduct(Long id) {
        final Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}