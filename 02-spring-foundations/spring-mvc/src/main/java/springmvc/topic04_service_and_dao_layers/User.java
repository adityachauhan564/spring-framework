package springmvc.topic04_service_and_dao_layers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * One row of the user table. It stores a password HASH, never the password itself.
 * (A hash is a scrambled one-way version: you can check a password against it,
 * but you cannot get the password back from it.)
 * The old version saved the plain password, and success.jsp even printed it back!
 */
@Entity
@Table(name = "app_user")               // not "user": that is a reserved word in several databases
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String passwordHash;

    protected User() {
    }

    public User(String userName, String email, String passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", userName=" + userName + ", email=" + email + "]";   // never print or log password data
    }
}
