package edu.school21.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({"edu.school21.cinema.services", "edu.school21.cinema.util"})
public class CoreConfig {
    private final Environment env;

    @Autowired
    public CoreConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public String storagePath() {
        return env.getProperty("storage.path");
    }
}
