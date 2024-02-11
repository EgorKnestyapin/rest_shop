package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Role;
import de.aittr.g_31_2_shop.domain.jpa.User;
import de.aittr.g_31_2_shop.exception_handling.exceptions.UserAlreadyExistsException;
import de.aittr.g_31_2_shop.repositories.jpa.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Transactional
    public User register(User user) {
        User foundUser = repository.findByUsername(user.getUsername());

        if (foundUser != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        user.setId(0);
        user.clearRoles();
        Role role = new Role(1, "ROLE_CUSTOMER");
        user.addRole(role);

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);
    }
}
