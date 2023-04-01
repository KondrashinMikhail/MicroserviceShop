package AuthorizationService.services;

import AuthorizationService.entities.User;
import AuthorizationService.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(String mail, String login, String password) {
        Long id = (long) (userRepository.findAll().size() + 1);
        final User user = new User(id, mail, login, password, 0.0);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, String mail, String login, String password, Double balance) {
        final User user = findUser(id);
        user.setMail(mail);
        user.setLogin(login);
        user.setPassword(password);
        user.setBalance(balance);
        userRepository.save(user);

    }

    @Transactional
    public void deleteUser(Long id) {
        final User user = findUser(id);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id) {
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
