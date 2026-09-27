package springmvc.topic04_service_and_dao_layers;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/*
 * DATA layer: only talks to the database (the spring-orm topic03 style).
 * No @Transactional here - the service decides where a transaction starts and ends.
 */
@Repository
public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User user) {
        sessionFactory.getCurrentSession().persist(user);
        return user;
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(User.class, id));
    }

    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User u order by u.id", User.class).list();
    }

    public boolean existsByEmail(String email) {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(u) from User u where lower(u.email) = lower(:email)", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }
}
