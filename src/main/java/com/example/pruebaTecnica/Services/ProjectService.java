package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.DTOS.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO, Long userId);

    List<ProjectDTO> getProjects();

    ProjectDTO getProject(Long projectId);

    ProjectDTO updateProject(ProjectDTO projectDTO, Long projectId, Long userId);

    void deleteProject(Long projectId);



}
