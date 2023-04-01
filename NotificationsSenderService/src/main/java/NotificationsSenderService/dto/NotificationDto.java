package NotificationsSenderService.dto;

import NotificationsSenderService.entities.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private String header;
    private String text;
    private Date date;
    private Long userId;

    public NotificationDto(Notification notification) {
        this.header = notification.getHeader();
        this.text = notification.getText();
        this.date = notification.getDate();
        this.userId = notification.getUserId();
    }
}
