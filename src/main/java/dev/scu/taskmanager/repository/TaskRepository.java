package dev.scu.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.scu.taskmanager.model.Task;
import dev.scu.taskmanager.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwnerOrderByDateDesc(User user);
    long countByIsCompleted(boolean isCompleted);

}