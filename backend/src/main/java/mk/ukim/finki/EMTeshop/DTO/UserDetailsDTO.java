package mk.ukim.finki.EMTeshop.DTO;

import lombok.Data;
import mk.ukim.finki.EMTeshop.Enum.Role;
import mk.ukim.finki.EMTeshop.Model.User;


@Data
public class UserDetailsDTO {

    private String username;
    private Role role;

    public static UserDetailsDTO of(User user) {
        UserDetailsDTO details = new UserDetailsDTO();
        details.username = user.getUsername();
        details.role = user.getRole();

        return details;
    }

}
