package com.example.pruebaTecnica.Converters;

import com.example.pruebaTecnica.DTOS.TaskDTO;
import com.example.pruebaTecnica.Entitys.Task;

public class TaskConverter {

    public static Task dtoToEntity(TaskDTO taskDTO){
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskState(taskDTO.getTaskState());
        return task;
    }
    public static TaskDTO entityToDto(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setTaskState(task.getTaskState());
        return taskDTO;
    }
}
