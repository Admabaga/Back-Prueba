package com.example.pruebaTecnica.DTOS;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private String startDate;
    private String endDate;
}
