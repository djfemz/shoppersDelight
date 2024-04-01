package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.models.Customer;
import africa.semicolon.shoppersDelight.services.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartService cartService;
    @Test
    void findByCartTest() throws CartNotFoundException {
        Optional<Customer> foundCustomer=
        customerRepository.findByCart(cartService.getCartBy(200L));

        assertThat(foundCustomer).isNotEmpty();
    }
}