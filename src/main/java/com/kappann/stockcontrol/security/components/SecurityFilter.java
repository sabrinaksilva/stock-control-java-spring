package com.kappann.stockcontrol.security.components;

import com.kappann.stockcontrol.configuration.security.JwtConfig;
import com.kappann.stockcontrol.security.context.CurrentUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private static final String ANONYMOUS = "Anonymous";
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = this.recoverToken(request);
        Authentication authentication = getAuthentication(jwtToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getJwtSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.getIssuer())
                .build();

        DecodedJWT decodedJWT;

        try {
            decodedJWT = verifier.verify(token);
            String username = decodedJWT.getClaim("sub").asString();
            String uuid = decodedJWT.getClaim("uuid").asString();
            List<String> roles = decodedJWT.getClaim("authorizations").asList(String.class);
            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            var principal = new CurrentUser(username, "", authorities, uuid);
            return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        } catch (JWTVerificationException ignored) {
            logger.info("User unauthorized");
        }
        return new AnonymousAuthenticationToken(ANONYMOUS, ANONYMOUS, List.of(new SimpleGrantedAuthority(ANONYMOUS)));

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
