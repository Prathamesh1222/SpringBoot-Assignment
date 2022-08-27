package com.project.service;

import com.project.controller.ProjectDetailsController;
import com.project.dto.ProjectDetailsDTO;
import com.project.dto.common.RestResponse;
import com.project.dto.transformer.DtoTransformerImpl;
import com.project.model.ProjectDetails;
import com.project.repository.ProjectDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDetailsServiceImpl.class);


    @Autowired
    private ProjectDetailsRepository repository;

    @Autowired
    private DtoTransformerImpl transformer;

    @Override
    public Optional<RestResponse> getAllProjects() {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        try {
            logger.info("Getting All project from db");
            List<ProjectDetails> projectDetails = repository.findAll();
            if (!projectDetails.isEmpty()) {
                response.setMessage("List of Project");
                response.setData("data", transformer.toDtoList(projectDetails));
            } else {
                response.setSuccess(false);
                response.setMessage("projects are not found");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Something went Wrong " + e.getMessage());
            logger.error("Exception while getting all project : "+e.getStackTrace());
            return Optional.of(response);
        }
        return Optional.of(response);
    }

    @Override
    public Optional<RestResponse> getProject(Integer projectId) {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        try {
            logger.info("Fetching project with projectId: "+projectId);
            Optional<ProjectDetails> byId = repository.findById(projectId);
            if (byId.isPresent()) {
                response.setMessage("Project Found");
                response.setData("data", transformer.toDto(byId.get()));
            } else {
                response.setSuccess(false);
                response.setMessage("Project not found");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Something went Wrong " + e.getMessage());
            logger.error("Exception while getting project : "+e.getStackTrace());
            return Optional.of(response);
        }
        return Optional.of(response);
    }

    @Override
    public Optional<RestResponse> addProject(ProjectDetailsDTO detailsDTO) {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        try {
            ProjectDetails projectDetails = repository.save(transformer.toEntity(detailsDTO));
            if (Objects.nonNull(projectDetails)) {
                response.setMessage("Project Added");
                response.setData("data", transformer.toDto(projectDetails));
            }else{
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Something went Wrong " + e.getMessage());
            logger.error("Exception while Adding project project : "+e.getStackTrace());
            return Optional.of(response);
        }
        return Optional.of(response);
    }

    @Override
    public Optional<RestResponse> deleteProject(Integer projectId) {

        RestResponse response = new RestResponse();
        response.setSuccess(true);
        try {
            repository.deleteById(projectId);
            response.setMessage("Project Deleted");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Something went Wrong " + e.getMessage());
            logger.error("Exception while deleting project : "+e.getStackTrace());
            return Optional.of(response);
        }
        return Optional.of(response);
    }

    @Override
    public Optional<RestResponse> updateProject(Integer projectId, ProjectDetails details) {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        try {
            Optional<ProjectDetails> byId = repository.findById(projectId);
            if(byId.isPresent()){
                ProjectDetails projectDetails = updateExistingProject(details, byId);
                ProjectDetails save = repository.save(projectDetails);
                response.setMessage("Project Updated");
                response.setData("data",transformer.toDto(save));
            }else {
                response.setSuccess(false);
                response.setMessage("Project Not Found");
            }

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Something went Wrong " + e.getMessage());
            logger.error("Exception while updating project : "+e.getStackTrace());
            return Optional.of(response);
        }
        return Optional.of(response);
    }

    public ProjectDetails updateExistingProject(ProjectDetails updated,Optional<ProjectDetails> entity){
        ProjectDetails details = entity.get();
        if (Objects.nonNull(updated.getManagerName()))
            details.setManagerName(updated.getManagerName());
        if(Objects.nonNull(updated.getBudget()))
            details.setBudget(updated.getBudget());
        if(Objects.nonNull(updated.getTitle()))
            details.setTitle(updated.getTitle());
        if(Objects.nonNull(updated.getTeamSize()))
            details.setTeamSize(updated.getTeamSize());
        if(Objects.nonNull(updated.getEndDate()))
            details.setEndDate(updated.getEndDate());
        return details;
    }
}
