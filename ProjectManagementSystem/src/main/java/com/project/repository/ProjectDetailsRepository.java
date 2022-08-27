package com.project.repository;

import com.project.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails,Integer> {


}
