package AuthorizationService.controllers;

import AuthorizationService.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/AuthorizationService/UserSynchronization", method = {RequestMethod.GET, RequestMethod.POST})
public class DatabaseUserSynchronizationController {
    private final UserService userService;

    public DatabaseUserSynchronizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/insert")
    public void insertUser(@RequestParam("mail") String mail,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password) {
        userService.addUser(mail, login, password);
    }
    @PostMapping("/update")
    public void updateUser(@RequestParam("id") Long id,
                           @RequestParam("mail") String mail,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("balance") Double balance) {
        userService.updateUser(id, mail, login, password, balance);
    }
    @PostMapping("/delete")
    public void deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
    }
}
