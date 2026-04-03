package ru.labs.netapps.spring.repo;

import ru.labs.netapps.spring.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task create(String title, String description, String variantCode);
    List<Task> findAll(String variantCode);
    Optional<Task> findById(long id, String variantCode);
}
