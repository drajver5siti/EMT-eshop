package mk.ukim.finki.EMTeshop.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.EMTeshop.DTO.UserDetailsDTO;
import mk.ukim.finki.EMTeshop.config.JWTAuthConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTAuthConstants.HEADER_STRING);
        if (header == null || !header.startsWith(JWTAuthConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String user = JWT.require(Algorithm.HMAC256(JWTAuthConstants.SECRET.getBytes()))
                .build()
                .verify(header.replace(JWTAuthConstants.TOKEN_PREFIX, ""))
                .getSubject();

        if(user == null) {
            return;
        }

        UserDetailsDTO userDetailsDTO = new ObjectMapper().readValue(user, UserDetailsDTO.class);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetailsDTO.getUsername(), "", Collections.singleton(userDetailsDTO.getRole()));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}
