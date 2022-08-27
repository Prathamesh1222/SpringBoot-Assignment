package com.project.dto.transformer;

import com.project.dto.ProjectDetailsDTO;
import com.project.model.ProjectDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DtoTransformerImpl implements DtoTransformer<ProjectDetails, ProjectDetailsDTO> {
    @Override
    public ProjectDetailsDTO toDto(ProjectDetails entity) {
        ProjectDetailsDTO dto = new ProjectDetailsDTO();
        if (Objects.nonNull(entity)) {
            dto.setProjectId(entity.getProjectId());
            dto.setTitle(entity.getTitle());
            dto.setBudget(entity.getBudget());
            dto.setManagerName(entity.getManagerName());
            dto.setEndDate(entity.getEndDate());
            dto.setTeamSize(entity.getTeamSize());
        }
        return dto;
    }

    @Override
    public ProjectDetails toEntity(ProjectDetailsDTO dto) {
        ProjectDetails entity = new ProjectDetails();
        if (Objects.nonNull(dto)) {
            entity.setProjectId(dto.getProjectId());
            entity.setTitle(dto.getTitle());
            entity.setBudget(dto.getBudget());
            entity.setTeamSize(dto.getTeamSize());
            entity.setManagerName(dto.getManagerName());
            entity.setEndDate(dto.getEndDate());
        }
        return entity;
    }

    @Override
    public List<ProjectDetailsDTO> toDtoList(List<ProjectDetails> entity) {
        if (entity != null && !entity.isEmpty()) {
            return entity
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<ProjectDetails> toEntityList(List<ProjectDetailsDTO> dto) {
        if (dto != null && !dto.isEmpty()) {
            return dto.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
