package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.Enum.Role;
import mk.ukim.finki.EMTeshop.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, Role role);
}
