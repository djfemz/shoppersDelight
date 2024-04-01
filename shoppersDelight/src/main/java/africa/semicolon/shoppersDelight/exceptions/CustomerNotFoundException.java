package africa.semicolon.shoppersDelight.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNotFoundException extends ShoppersDelightBaseException {
    private final Logger logger = LoggerFactory.getLogger(CustomerNotFoundException.class);
    public CustomerNotFoundException(String message) {

        super(message);
        logger.error("error: {}", message);
    }
}
