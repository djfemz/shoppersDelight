package africa.semicolon.shoppersDelight.security.config;

import africa.semicolon.shoppersDelight.security.filters.AppAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static africa.semicolon.shoppersDelight.models.Role.ADMIN;
import static africa.semicolon.shoppersDelight.models.Role.CUSTOMER;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        UsernamePasswordAuthenticationFilter filter = new AppAuthenticationFilter(authenticationManager);
        return http.cors(Customizer.withDefaults())
                .csrf(c->c.disable())
                .addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers("/login").permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(GET,"/api/v1/customer")
                        .hasAnyAuthority(CUSTOMER.name(), ADMIN.name()))
                .build();
    }
}
