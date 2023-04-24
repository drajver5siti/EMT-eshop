package mk.ukim.finki.EMTeshop.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import mk.ukim.finki.EMTeshop.DTO.UserDetailsDTO;
import mk.ukim.finki.EMTeshop.Model.User;
import mk.ukim.finki.EMTeshop.config.JWTAuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User creds = null;
        try {
            String text = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("HELLO WORLD, " + text);
            creds = new ObjectMapper().readValue(text, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(creds == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(creds.getUsername());

        if(!passwordEncoder.matches(creds.getPassword(), userDetails.getPassword()))
        {
            throw new UsernameNotFoundException("Invalid password");
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        creds.getPassword(),
                        userDetails.getAuthorities()
                )
        );

//        return new UsernamePasswordAuthenticationToken(
//                userDetails.getUsername(),
//                creds.getPassword(),
//                userDetails.getAuthorities()
//        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.generateJWT(response, authResult);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    public String generateJWT(HttpServletResponse response, Authentication authResult) throws JsonProcessingException {
        User userDetails = (User) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDTO.of(userDetails)))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTAuthConstants.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET));

        response.addHeader(JWTAuthConstants.HEADER_STRING, JWTAuthConstants.TOKEN_PREFIX + token);
        return JWTAuthConstants.TOKEN_PREFIX + token;
    }
}
