package org.example.fintechproject.repository;

import org.example.fintechproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTaskNameAndTaskType(String taskName, String taskType);
}
