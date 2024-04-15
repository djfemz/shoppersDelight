package africa.semicolon.shoppersDelight.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

}
