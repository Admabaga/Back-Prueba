package com.example.pruebaTecnica.Controller;

import com.example.pruebaTecnica.DTOS.TaskDTO;
import com.example.pruebaTecnica.Services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/users/{userId}/projects/{projectId}/tasks")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO, @PathVariable Long userId, @PathVariable Long projectId) {
        TaskDTO task = taskService.createTask(taskDTO, userId, projectId);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskDTO> getTaskByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @PutMapping("/users/{userId}/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long taskId, @PathVariable Long userId, @PathVariable Long projectId) {
        TaskDTO updatedTask = taskService.updateTask(taskDTO, taskId, userId, projectId);
        return ResponseEntity.ok(updatedTask);
    }

}
