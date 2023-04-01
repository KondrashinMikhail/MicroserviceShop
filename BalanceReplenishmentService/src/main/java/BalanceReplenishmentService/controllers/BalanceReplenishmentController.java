package BalanceReplenishmentService.controllers;

import BalanceReplenishmentService.entities.User;
import BalanceReplenishmentService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/BalanceReplenishmentService", method = {RequestMethod.GET, RequestMethod.POST})
public class BalanceReplenishmentController {
    private final UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public BalanceReplenishmentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/replenish")
    public String replenish(@RequestParam("id") Long id,
                            @RequestParam("additionalSum") Double additionalSum) {
        final User user = userService.findUser(id);
        double newBalance = user.getBalance() + additionalSum;
        restTemplate.getForObject(
                "http://localhost:90/UserSynchronizationService/update?id=" + id + "&mail=" + user.getMail() + "&login=" + user.getLogin() + "&password=" + user.getPassword() + "&balance=" + newBalance,
                Void.class);
        return "NEW BALANCE IS: " + newBalance;
    }

    @PostMapping("/subtract")
    public String subtract(@RequestParam("id") Long id,
                            @RequestParam("subtractionSum") Double subtractionSum) {
        final User user = userService.findUser(id);
        double newBalance = user.getBalance() - subtractionSum;
        restTemplate.getForObject(
                "http://localhost:90/UserSynchronizationService/update?id=" + id + "&mail=" + user.getMail() + "&login=" + user.getLogin() + "&password=" + user.getPassword() + "&balance=" + newBalance,
                Void.class);
        return "NEW BALANCE IS: " + newBalance;
    }

}
