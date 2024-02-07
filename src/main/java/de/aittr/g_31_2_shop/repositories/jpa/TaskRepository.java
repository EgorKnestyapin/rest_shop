package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM task ORDER BY id DESC LIMIT :n", nativeQuery = true)
    List<Task> findNLastTasks(@Param("n") Integer n);
}
