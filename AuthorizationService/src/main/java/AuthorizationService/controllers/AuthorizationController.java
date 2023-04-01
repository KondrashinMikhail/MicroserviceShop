package AuthorizationService.controllers;

import AuthorizationService.entities.User;
import AuthorizationService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/AuthorizationService", method = {RequestMethod.GET, RequestMethod.POST})
public class AuthorizationController {
    private final UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public String signUp(@RequestParam("mail") String mail,
                         @RequestParam("login") String login,
                         @RequestParam("password") String password) {
        restTemplate.getForObject(
                "http://localhost:90/UserSynchronizationService/insert?mail=" + mail + "&login=" + login + "&password=" + password,
                Void.class);
        return "SIGNED UP";
    }

    @PostMapping("/signIn")
    public User signIn(@RequestParam("login") String login,
                         @RequestParam("password") String password) {
        List<User> users = userService.findAllUsers();
        for (User user : users)
            if (Objects.equals(user.getLogin().trim(), login) && Objects.equals(user.getPassword().trim(), password))
                return user;
        return null;
    }
}
