package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.Converters.TaskConverter;
import com.example.pruebaTecnica.DTOS.TaskDTO;
import com.example.pruebaTecnica.Entitys.Project;
import com.example.pruebaTecnica.Entitys.Task;
import com.example.pruebaTecnica.Entitys.User;
import com.example.pruebaTecnica.Repositories.ProjectRepository;
import com.example.pruebaTecnica.Repositories.TaskRepository;
import com.example.pruebaTecnica.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO, Long userId, Long projectId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!userOptional.isPresent()){
            throw new RuntimeException(String.format("El usuario con id %s no es valido.", userId));
        }
        if (!projectOptional.isPresent()){
            throw new RuntimeException(String.format("El proyecto con id %s no es valido.", projectId));
        }
        Project project = projectOptional.get();
        User user = userOptional.get();
        Task task = TaskConverter.dtoToEntity(taskDTO);
        task.setUser(user);
        task.setProject(project);
        taskRepository.save(task);
        return TaskConverter.entityToDto(task);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO, Long taskId, Long userId, Long projectId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(!taskOptional.isPresent()){
            throw new RuntimeException("La tarea que deseas actualizar no existe.");
        }
        if(!userOptional.isPresent()){
            throw new RuntimeException("El usuario no existe.");
        }
        if(!projectOptional.isPresent()){
            throw new RuntimeException("El proyecto no existe.");
        }
        User user = userOptional.get();
        Project project = projectOptional.get();
        Task task = taskOptional.get();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskState(taskDTO.getTaskState());
        task.setUser(user);
        task.setProject(project);
        taskRepository.save(task);
        return TaskConverter.entityToDto(task);
    }

    @Override
    public List<TaskDTO> getTasksByProject(Long projectId) {
        List<Task> taskOptional = taskRepository.getByProjectId(projectId);
        return taskOptional.stream()
                .map(TaskConverter::entityToDto)
                .sorted(Comparator.comparing(TaskDTO::getTaskId).reversed())
                .collect(Collectors.toList());
    }
}
