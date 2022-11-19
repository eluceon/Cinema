package edu.school21.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"edu.school21.cinema.services", "edu.school21.cinema.util"})
public class CoreConfig {
}
