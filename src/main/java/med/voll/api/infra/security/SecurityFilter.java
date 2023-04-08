package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // generic component (use when you need the spring load this class)
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = retrieveTokenJWT(request);
        if (tokenJWT != null ) {
            var subject = tokenService.getSubject(tokenJWT); // validating if the JWT token is valid
            var user = userRepository.findByUsername(subject); // retrieve the user in the repository

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // create the DTO that represent the user
            SecurityContextHolder.getContext().setAuthentication(authentication); // force the authentication
        }
        filterChain.doFilter(request, response); // calling the next filter
    }

    private String retrieveTokenJWT(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer ", ""); // extract the token with prefix
        }
        return null;
    }
}
