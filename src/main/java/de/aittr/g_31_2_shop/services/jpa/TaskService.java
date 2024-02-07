package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.repositories.jpa.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void createTask(String description) {
        logger.info(description);
        Task task = new Task(description);
        repository.save(task);
    }

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public List<Task> getNLastTasks(int n) {
        return repository.findNLastTasks(n);
    }
}
