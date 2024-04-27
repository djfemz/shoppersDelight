package africa.semicolon.shoppersDelight.security.filters;

import africa.semicolon.shoppersDelight.dtos.request.AuthRequest;
import africa.semicolon.shoppersDelight.exceptions.AuthenticationFailedException;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Slf4j
public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //1. extract authentication credentials from body of request
            InputStream inputStream = request.getInputStream(); //reads from request body
            AuthRequest authRequest = mapper.readValue(inputStream, AuthRequest.class);
            log.info("req-->{}", authRequest);
            //2i. Create an object of type Authentication that is not yet authenticated.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            //2ii. send the unauthenticated Authentication object to AuthenticationManager.
            Authentication authenticationResult = authenticationManager.authenticate(authentication);

            log.info("authResult:: {}", authenticationResult);
            boolean isAuthenticationSuccessful = authenticationResult.isAuthenticated();
            if (isAuthenticationSuccessful) {
                SecurityContextHolder.getContext().setAuthentication(authenticationResult);
                return authenticationResult;
            }
        }catch (IOException exception){
            throw new BadCredentialsException("Authentication Failed for user");
        }
        throw new AuthenticationFailedException("Authentication failed");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String accessToken = JWT.create()
                .withIssuer("shoppers_delight")
                .withSubject("access_token")
                .withClaim("created_at", new Date())
                .withClaim("username", authResult.getPrincipal().toString())
                .withExpiresAt(Instant.now().plusSeconds(86400 * 7))
                .sign(HMAC256("this is our very secret key"));
        Map<String, String> authResponse = new HashMap<>();
        authResponse.put("access_token", accessToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(authResponse));
        response.setStatus(OK.value());
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> authResponse = new HashMap<>();
        authResponse.put("error", "authentication failed for user");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(authResponse));
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.flushBuffer();
    }
}
