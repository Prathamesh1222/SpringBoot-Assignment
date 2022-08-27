package com.project.service;

import com.project.dto.ProjectDetailsDTO;
import com.project.dto.common.RestResponse;
import com.project.model.ProjectDetails;

import java.util.Optional;

public interface ProjectDetailsService {

    public Optional<RestResponse> getAllProjects();

    public Optional<RestResponse> getProject(Integer projectId);

    public Optional<RestResponse> addProject(ProjectDetailsDTO detailsDTO);

    public Optional<RestResponse> deleteProject(Integer projectId);

    public Optional<RestResponse> updateProject(Integer projectId, ProjectDetails details);
}
