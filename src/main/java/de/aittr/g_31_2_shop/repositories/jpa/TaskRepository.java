package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {}
