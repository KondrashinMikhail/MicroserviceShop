package PurchaseService.services;

import PurchaseService.entities.ProductDiscount;
import PurchaseService.repositories.ProductDiscountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDiscountService {

    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountService(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    @Transactional
    public void addValue(Long productId, Long discountId) {
        Long id = (long) (productDiscountRepository.findAll().size() + 1);
        final ProductDiscount value = new ProductDiscount(id, productId, discountId);
        productDiscountRepository.save(value);
    }

    @Transactional
    public void updateValue(Long id, Long productId, Long discountId) {
        final ProductDiscount value = findById(id);
        value.setProductId(productId);
        value.setDiscountId(discountId);
    }

    @Transactional
    public void deleteValue(Long id) {
        final ProductDiscount value = findById(id);
        productDiscountRepository.delete(value);
    }

    @Transactional(readOnly = true)
    public List<ProductDiscount> findAll() {
        return productDiscountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ProductDiscount findById(Long id) {
        final Optional<ProductDiscount> value = productDiscountRepository.findById(id);
        return value.orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<ProductDiscount> findByProductId(Long productId) {
        return productDiscountRepository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<ProductDiscount> findByDiscountIdId(Long discountId) {
        return productDiscountRepository.findByDiscountId(discountId);
    }
}
