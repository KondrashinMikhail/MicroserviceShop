package UserSynchronizationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/UserSynchronizationService", method = {RequestMethod.GET, RequestMethod.POST})
public class UserSynchronizationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/insert")
    public void insert(@RequestParam("mail") String mail,
                       @RequestParam("login") String login,
                       @RequestParam("password") String password) {
        restTemplate.getForObject(
                "http://localhost:81/AuthorizationService/UserSynchronization/insert?mail=" + mail + "&login=" + login + "&password=" + password,
                Void.class);
        restTemplate.getForObject(
                "http://localhost:82/BalanceReplenishmentService/UserSynchronization/insert?mail=" + mail + "&login=" + login + "&password=" + password,
                Void.class);
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Long id,
                       @RequestParam("mail") String mail,
                       @RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("balance") Double balance) {
        restTemplate.getForObject(
                "http://localhost:81/AuthorizationService/UserSynchronization/update?id=" + id + "&mail=" + mail + "&login=" + login + "&password=" + password + "&balance=" + balance,
                Void.class);
        restTemplate.getForObject(
                "http://localhost:82/BalanceReplenishmentService/UserSynchronization/update?id=" + id + "&mail=" + mail + "&login=" + login + "&password=" + password + "&balance=" + balance,
                Void.class);
    }
}
