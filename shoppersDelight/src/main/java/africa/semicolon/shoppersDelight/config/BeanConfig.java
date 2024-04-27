package africa.semicolon.shoppersDelight.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Getter
public class BeanConfig {
    @Value("${paystack.base.url}")
    private String paystackBaseUrl;
    @Value("${paystack.api.key}")
    private String paystackApiKey;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
