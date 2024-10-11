package com.example.pruebaTecnica.DTOS;

import lombok.Data;

@Data
public class TaskDTO {
    private Long  taskId;
    private String taskName;
    private String taskDescription;
    private String taskState;
}
