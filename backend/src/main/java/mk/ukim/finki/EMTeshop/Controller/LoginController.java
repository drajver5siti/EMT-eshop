package mk.ukim.finki.EMTeshop.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.EMTeshop.config.filters.JWTAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/login")
public class LoginController {

    private final JWTAuthenticationFilter filter;

    public LoginController(JWTAuthenticationFilter filter) {
        this.filter = filter;
    }

    @PostMapping
    public String doLogin(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        Authentication auth = this.filter.attemptAuthentication(request, response);
        return this.filter.generateJWT(response, auth);
    }
}
