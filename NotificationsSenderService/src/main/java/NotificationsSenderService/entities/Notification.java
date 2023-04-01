package NotificationsSenderService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "notification", schema = "public")
public class Notification {
    @Id
    private Long id;
    private String header;
    private String text;
    private Date date;
    private Long userId;
    public Notification(String header, String text, Date date, Long userId) {
        this.header = header;
        this.text = text;
        this.date = date;
        this.userId = userId;
    }
}
