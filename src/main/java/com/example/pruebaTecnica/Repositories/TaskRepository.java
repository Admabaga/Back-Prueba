package com.example.pruebaTecnica.Repositories;

import com.example.pruebaTecnica.Entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId")
    List<Task> getByProjectId(@Param("projectId") Long projectId);
}
