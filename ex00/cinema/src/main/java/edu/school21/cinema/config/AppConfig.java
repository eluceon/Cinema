package edu.school21.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "edu.school21.cinema.controllers")
public class AppConfig {
}
