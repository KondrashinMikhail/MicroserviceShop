package PurchaseService.services;

import PurchaseService.entities.Discount;
import PurchaseService.repositories.DiscountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }
    @Transactional
    public void addDiscount(int duration, Double amount) {
        Long id = (long) (discountRepository.findAll().size() + 1);
        final Discount discount = new Discount(id, new Date(), duration, amount);
        discountRepository.save(discount);
    }

    @Transactional
    public void updateDiscount(Long id, Date creationDate, int duration, Double amount) {
        final Discount discount = findDiscount(id);
        discount.setCreationDate(creationDate);
        discount.setDuration(duration);
        discount.setAmount(amount);
    }

    @Transactional
    public void deleteDiscount(Long id) {
        final Discount discount = findDiscount(id);
        discountRepository.delete(discount);
    }

    @Transactional(readOnly = true)
    public List<Discount> findAllDiscounts() {
        return discountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Discount findDiscount(Long id) {
        final Optional<Discount> discount = discountRepository.findById(id);
        return discount.orElseThrow();
    }
}
