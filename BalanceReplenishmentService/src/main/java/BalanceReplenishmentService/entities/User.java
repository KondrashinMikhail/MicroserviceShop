package BalanceReplenishmentService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", schema = "public")
public class User {

    @Id
    private Long id;
    private String mail;
    private String login;
    private String password;
    private Double balance;

    public User(String mail, String login, String password, Double balance) {
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.balance = balance;
    }
}