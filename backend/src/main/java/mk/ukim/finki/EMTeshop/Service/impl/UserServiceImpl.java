package mk.ukim.finki.EMTeshop.Service.impl;

import mk.ukim.finki.EMTeshop.Enum.Role;
import mk.ukim.finki.EMTeshop.Exception.InvalidUsernameOrPasswordException;
import mk.ukim.finki.EMTeshop.Exception.UsernameAlreadyExistsException;
import mk.ukim.finki.EMTeshop.Model.User;
import mk.ukim.finki.EMTeshop.Repository.UserRepository;
import mk.ukim.finki.EMTeshop.Service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User register(String username, String password, Role role) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }
}
