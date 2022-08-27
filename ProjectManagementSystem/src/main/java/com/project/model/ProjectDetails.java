package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_details")
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_title")
    private String title;

    @Column(name = "project_budget")
    private Double budget;

    @Column(name = "team_size")
    private Integer teamSize;

    @Column(name = "project_end_date")
    private LocalDate endDate;

    @Column(name = "project_manager_name")
    private String managerName;
}
