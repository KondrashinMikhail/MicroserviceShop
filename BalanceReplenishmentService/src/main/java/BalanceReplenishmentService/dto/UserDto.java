package BalanceReplenishmentService.dto;

import BalanceReplenishmentService.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String mail;
    private String login;
    private String password;
    private Double balance;

    public UserDto(User user) {
        id = user.getId();
        mail = user.getMail();
        login = user.getLogin();
        password = user.getPassword();
        balance = user.getBalance();
    }
}
