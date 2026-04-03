package ru.labs.netapps.spring.repo;

import ru.labs.netapps.spring.model.Task;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTaskRepository implements TaskRepository {
    private final AtomicLong seq = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Task> data = new ConcurrentHashMap<>();

    @Override
    public Task create(String title, String description, String variantCode) {
        long id = seq.incrementAndGet();
        Task t = new Task(id, title, description, OffsetDateTime.now(), variantCode);
        data.put(id, t);
        return t;
    }

    @Override
    public List<Task> findAll(String variantCode) {
        return data.values().stream()
                .filter(t -> t.variantCode().equals(variantCode))
                .sorted(Comparator.comparing(Task::createdAt).reversed())
                .toList();
    }

    @Override
    public Optional<Task> findById(long id, String variantCode) {
        Task t = data.get(id);
        if (t == null) return Optional.empty();
        if (!t.variantCode().equals(variantCode)) return Optional.empty();
        return Optional.of(t);
    }
}
