package com.example.pruebaTecnica.Services;

import com.example.pruebaTecnica.DTOS.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO, Long user, Long projectId);

    TaskDTO updateTask(TaskDTO taskDTO, Long TaskId, Long userId, Long projectId);

    List<TaskDTO> getTasksByProject(Long projectId);

}
