package com.example.pruebaTecnica.Converters;

import com.example.pruebaTecnica.DTOS.ProjectDTO;
import com.example.pruebaTecnica.Entitys.Project;

public class ProjectConverter {

    public static Project dtoToEntity(ProjectDTO projectDTO){
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        return project;
    }

    public static ProjectDTO entityToDto(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setProjectDescription(project.getProjectDescription());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        return projectDTO;
    }
}
