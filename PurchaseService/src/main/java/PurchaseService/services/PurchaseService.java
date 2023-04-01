package PurchaseService.services;

import PurchaseService.entities.Purchase;
import PurchaseService.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public void addPurchase(Double price, Long productId, Long userId) {
        Long id = (long) (purchaseRepository.findAll().size() + 1);
        Purchase purchase = new Purchase(id, price, new Date(), productId, userId);
        purchaseRepository.save(purchase);
    }

    @Transactional
    public void updatePurchase(Long id, Double price, Long productId, Long userId) {
        final Purchase purchase = findPurchase(id);
        purchase.setPrice(price);
        purchase.setProductId(productId);
        purchase.setUserId(userId);
    }

    @Transactional
    public void deletePurchase(Long id) {
        final Purchase purchase = findPurchase(id);
        purchaseRepository.delete(purchase);
    }

    @Transactional(readOnly = true)
    public void findAllPurchases(){}

    @Transactional(readOnly = true)
    public Purchase findPurchase(Long id) {
        final Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Purchase> findPurchaseByProductId(Long productId) {
        return purchaseRepository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Purchase> findPurchaseByUserId(Long userId) {
        return purchaseRepository.findByUserId(userId);
    }
}
