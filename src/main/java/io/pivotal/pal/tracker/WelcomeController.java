package io.pivotal.pal.tracker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private String message;
    private final Log logger = LogFactory.getLog(WelcomeController.class);

    public WelcomeController(@Value("${WELCOME_MESSAGE}")String message) {
        this.message = message;
    }

    @GetMapping
    public String sayHello(){
        logger.info("inside sayHello()........");
        return message;
    }

}
