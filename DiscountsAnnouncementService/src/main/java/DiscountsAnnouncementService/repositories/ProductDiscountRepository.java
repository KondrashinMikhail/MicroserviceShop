package DiscountsAnnouncementService.repositories;

import DiscountsAnnouncementService.entities.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long> {
    List<ProductDiscount> findByProductId(Long productId);
    List<ProductDiscount> findByDiscountId(Long discountId);
}
