package com.gfg.showtime.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gfg.showtime.enums.Role;
import com.gfg.showtime.resource.UserResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The user entity is also Spring Security's UserDetails, so the security layer can use it directly.
 * The login name is the EMAIL, because it is unique. (The course looked users up by name but returned
 * the email as the username, so nobody could log in - and two people can have the same name anyway.)
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;               // a BCrypt hash - never the password itself

	@Column(nullable = false, unique = true)
	private String mobile;

	@Column(nullable = false, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Ticket> tickets = new ArrayList<>();

	public static UserResource toResource(User user) {
		return new UserResource(user.getId(), user.getName(), user.getMobile(), user.getEmail(), user.getRole(),
				Ticket.toResource(user.getTickets()));
	}

	// "ADMIN" or "USER". The security rules check these with hasAuthority(...)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}
}
