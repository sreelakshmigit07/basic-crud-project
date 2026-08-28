package org.example.fintechproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDTO {

    @NotBlank(message = "Task name is required")
    private String taskName;
    private String taskType;
}
