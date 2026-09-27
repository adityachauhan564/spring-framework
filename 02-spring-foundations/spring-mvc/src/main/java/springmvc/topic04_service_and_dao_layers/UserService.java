package springmvc.topic04_service_and_dao_layers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Topic    : Layers - controller -> service -> DAO -> database
 * Key idea : each layer has ONE job:
 *              controller - HTTP: read the request, pick the view            (topic02/05/07)
 *              service    - business rules and the transaction boundary      (here)
 *              DAO        - SQL / Hibernate calls only                        (UserDao)
 *            Both the web form (topic05) and the JSON API (topic07) reuse this same service.
 *            Passwords are hashed with BCrypt before they reach the DAO: a hash can be
 *            checked (matches) but not reversed, and every hash has its own random salt.
 */
@Service
@Transactional
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String userName, String email, String rawPassword) {
        if (userDao.existsByEmail(email)) {
            throw new IllegalArgumentException("email already registered: " + email);
        }
        return userDao.save(new User(userName, email, passwordEncoder.encode(rawPassword)));
    }

    @Transactional(readOnly = true)
    public boolean isEmailTaken(String email) {
        return userDao.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(int id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public boolean checkPassword(int userId, String rawPassword) {
        return userDao.findById(userId)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .orElse(false);
    }
}
