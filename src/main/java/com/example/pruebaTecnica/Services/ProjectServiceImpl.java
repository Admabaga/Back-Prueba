package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.Converters.ProjectConverter;
import com.example.pruebaTecnica.Converters.TaskConverter;
import com.example.pruebaTecnica.DTOS.ProjectDTO;
import com.example.pruebaTecnica.Entitys.Project;
import com.example.pruebaTecnica.Entitys.Task;
import com.example.pruebaTecnica.Entitys.User;
import com.example.pruebaTecnica.Repositories.ProjectRepository;
import com.example.pruebaTecnica.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public ProjectServiceImpl(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()){
            throw new RuntimeException( String.format("El usuario con id %s no es valido", userId));
        }
        User user = userOptional.get();
        Project project = ProjectConverter.dtoToEntity(projectDTO);
        projectValidations(project);
        project.setUser(user);
        projectRepository.save(project);
        return ProjectConverter.entityToDto(project);
    }

    @Override
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectConverter::entityToDto)
                .sorted(Comparator.comparing(ProjectDTO::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!projectOptional.isPresent()){
            throw new RuntimeException(String.format("El proyecto con id %s no es valido.", projectId));
        }
        Project project = projectOptional.get();
        return ProjectConverter.entityToDto(project);
    }

    @Override
    public ProjectDTO updateProject( ProjectDTO projectDTO, Long projectId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("El usuario no existe.");
        }
        if(!projectOptional.isPresent()){
            throw new RuntimeException("El proyecto no existe.");
        }
        User user = userOptional.get();
        Project project = projectOptional.get();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setEndDate(projectDTO.getEndDate());
        projectDTO.setStartDate(projectDTO.getStartDate());
        project.setUser(user);
        projectRepository.save(project);
        return ProjectConverter.entityToDto(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(!projectOptional.isPresent()){
            throw new RuntimeException(String.format("El proyecto que con id %s no es valido.", projectId));
        }
        projectRepository.deleteById(projectId);
    }

    public void projectValidations(Project project){
        if (project.getProjectName()== null || String.valueOf(project.getProjectName())== "" ) {
            throw new RuntimeException("El campo nombre debe estar diligenciado.");
        }
        if (project.getProjectDescription()== null || project.getProjectDescription()== "" ) {
            throw new RuntimeException("El campo descripcion debe estar diligenciado.");
        }
        if (project.getStartDate()== null || project.getStartDate()== "" ) {
            throw new RuntimeException("El campo fecha de inicio debe estar diligenciado.");
        }
        if (project.getEndDate()== null || project.getEndDate()== "" ) {
            throw new RuntimeException("El campo fecha a finalizar debe estar diligenciado.");
        }
    }
}
