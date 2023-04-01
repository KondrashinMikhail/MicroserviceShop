package FeedbackCreationService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback", schema = "public")
public class Feedback {
    @Id
    private Long id;
    private Date date;
    private Double estimate;
    private String text;
    private Long userId;
    private Long productId;

    public Feedback(Date date, Double estimate, String text, Long userId, Long productId) {
        this.date = date;
        this.estimate = estimate;
        this.text = text;
        this.userId = userId;
        this.productId = productId;
    }
}
