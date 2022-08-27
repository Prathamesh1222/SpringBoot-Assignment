package com.project.controller;

import com.project.dto.ProjectDetailsDTO;
import com.project.dto.common.RestResponse;
import com.project.model.ProjectDetails;
import com.project.service.ProjectDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
public class ProjectDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDetailsController.class);


    @Autowired
    private ProjectDetailsService service;

    /*
    * @Auther prathamesh
    * API to ADD Project
    */
    @PostMapping
    public ResponseEntity<RestResponse> addProject(@Valid @RequestBody ProjectDetailsDTO dto){
        logger.info("Request For Add Project Parameter : "+dto);
        RestResponse response = service.addProject(dto).orElse(null);
        HttpStatus status = HttpStatus.CONFLICT;
        if(null!=response && response.isSuccess())
            status=HttpStatus.CREATED;
        return new ResponseEntity<>(response,status);
    }

    /*
     * @Auther prathamesh
     * API to get All Projects
     */
    @GetMapping(value = "/list")
    public ResponseEntity<RestResponse> getAllProjects(){
        logger.info("Request for All Projects");
        RestResponse response = service.getAllProjects().orElse(null);
        HttpStatus status = HttpStatus.CONFLICT;
        if(null!=response && response.isSuccess())
            status=HttpStatus.OK;
        return new ResponseEntity<>(response,status);
    }

    /*
     * @Auther prathamesh
     * API to get Project
     */
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse> getProjectByIf(@PathVariable Integer id){
        logger.info("Request for Project with projectId: "+id);
        RestResponse response = service.getProject(id).orElse(null);
        HttpStatus status = HttpStatus.CONFLICT;
        if(null!=response && response.isSuccess())
            status=HttpStatus.OK;
        return new ResponseEntity<>(response,status);
    }

    /*
     * @Auther prathamesh
     * API to delete Project
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse> deleteProject(@PathVariable Integer id){
        logger.info("Request for delete Project with projectId: "+id);
        RestResponse response = service.deleteProject(id).orElse(null);
        HttpStatus status = HttpStatus.CONFLICT;
        if(null!=response && response.isSuccess())
            status=HttpStatus.OK;
        return new ResponseEntity<>(response,status);
    }

    /*
     * @Auther prathamesh
     * API to update Project
     */
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse> update(@PathVariable Integer id, @RequestBody ProjectDetails details){
        logger.info("Request for update Project with projectId: "+id);
        RestResponse response = service.updateProject(id,details).orElse(null);
        HttpStatus status = HttpStatus.CONFLICT;
        if(null!=response && response.isSuccess())
            status=HttpStatus.OK;
        return new ResponseEntity<>(response,status);
    }
}
