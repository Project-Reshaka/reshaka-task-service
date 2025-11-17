package ru.reshaka.taskengine.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "ru.reshaka.taskengine.infra.postgre.repo")
public class PostgreConfig {


}
