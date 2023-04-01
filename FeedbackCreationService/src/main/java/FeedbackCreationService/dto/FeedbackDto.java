package FeedbackCreationService.dto;

import FeedbackCreationService.entities.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private Long id;
    private Date date;
    private Double estimate;
    private String text;
    private Long userId;
    private Long productId;

    public FeedbackDto(Feedback feedback) {
        this.id = feedback.getId();
        this.date = feedback.getDate();
        this.estimate = feedback.getEstimate();
        this.text = feedback.getText();
        this.userId = feedback.getUserId();
        this.productId = feedback.getProductId();
    }
}
