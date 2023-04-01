package DiscountsAnnouncementService.dto;

import DiscountsAnnouncementService.entities.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private Long id;
    private Date creationDate;
    private int duration;
    private Double amount;

    public DiscountDto(Discount discount) {
        this.id = discount.getId();
        this.creationDate = discount.getCreationDate();
        this.duration = discount.getDuration();
        this.amount = discount.getAmount();
    }
}
