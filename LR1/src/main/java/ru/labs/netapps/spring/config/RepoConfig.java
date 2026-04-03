package ru.labs.netapps.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.labs.netapps.spring.repo.InMemoryTaskRepository;
import ru.labs.netapps.spring.repo.TaskRepository;

@Configuration
public class RepoConfig {

    @Bean
    public TaskRepository inMemoryRepo() {
        return new InMemoryTaskRepository();
    }
}
