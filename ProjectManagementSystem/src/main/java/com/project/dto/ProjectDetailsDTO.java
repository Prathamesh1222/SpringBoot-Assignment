package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDTO {

     @NotNull(message = "Project id cannot be null")
    private Integer projectId;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Budget cannot be null")
    private Double budget;

    @NotNull(message = "Team size cannot be null")
    private Integer teamSize;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "End Date cannot be null")
    private LocalDate endDate;

    @NotNull(message = "Manager name cannot be null")
    private String managerName;
}
