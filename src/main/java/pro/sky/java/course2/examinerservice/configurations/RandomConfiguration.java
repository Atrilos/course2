package pro.sky.java.course2.examinerservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class RandomConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    Random random() {
        return new Random();
    }
}
