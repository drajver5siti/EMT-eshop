package mk.ukim.finki.EMTeshop.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.EMTeshop.Service.UserService;
import mk.ukim.finki.EMTeshop.config.filters.JWTAuthenticationFilter;
import mk.ukim.finki.EMTeshop.config.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableGlobalAuthentication
@AllArgsConstructor
@Profile("jwt")
public class JWTWebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        if (authenticationManager == null) {
            throw new IllegalStateException("No authentication manager shared object found in the HTTP security configuration.");
        }


        http
                .cors()
                .and().csrf().disable()
//                .authorizeHttpRequests()
//                    .requestMatchers("/", "/home", "/api/**").permitAll()
//                    .anyRequest().authenticated()
//                .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager, userService, passwordEncoder))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
