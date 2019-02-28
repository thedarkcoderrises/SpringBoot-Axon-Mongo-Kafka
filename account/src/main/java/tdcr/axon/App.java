package tdcr.axon;

import org.axonframework.spring.config.EnableAxon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAxon
@ComponentScan("tdcr.axon")
public class App 
{
    private static Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        LOG.info( "e-cart is up and running!" );
    }
}
