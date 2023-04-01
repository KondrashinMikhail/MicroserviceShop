package NotificationsSenderService.controllers;

import NotificationsSenderService.dto.NotificationDto;
import NotificationsSenderService.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/NotificationsSenderService", method = {RequestMethod.GET, RequestMethod.POST})
public class NotificationSenderController {
    private final NotificationService notificationService;

    public NotificationSenderController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam("header") String header,
                                   @RequestParam("text") String text,
                                   @RequestParam("userId") Long userId) {
        notificationService.addNotification(header, text, userId);
        return "NOTIFICATION SEND";
    }

    @PostMapping("/user/{userId}/getNotifications")
    public List<NotificationDto> getUserNotifications(@PathVariable Long userId) {
        return notificationService.findNotificationsByUserId(userId).stream().map(NotificationDto::new).toList();

    }
}
