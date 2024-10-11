package com.example.pruebaTecnica.Controller;

import com.example.pruebaTecnica.DTOS.ProjectDTO;
import com.example.pruebaTecnica.Services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/users/{userId}/projects")
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long userId) {
        ProjectDTO project = projectService.createProject(projectDTO, userId);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{projectId}")
    public ProjectDTO getProject(@PathVariable Long projectId) {
        return projectService.getProject(projectId);
    }

    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(@PathVariable Long projectId) { projectService.deleteProject(projectId);
    }

    @PutMapping("/users/{userId}/projects{projectId}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long userId, @PathVariable Long projectId) {
        ProjectDTO project = projectService.updateProject(projectDTO, projectId, userId);
        return ResponseEntity.ok(project);
    }
}
