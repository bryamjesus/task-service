package com.code.mapper;

import com.code.dto.request.ProjectRequest;
import com.code.dto.response.ProjectResponse;
import com.code.model.Project;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProjectMapper {
    private final ModelMapper modelMapper;

    public Project projectRequestToProject(ProjectRequest request) {
        return modelMapper.map(request, Project.class);
    }

    public ProjectResponse projectToProjectResponse(Project project) {
        return modelMapper.map(project, ProjectResponse.class);
    }

    public List<Project> projectRequestListToProjectList(List<ProjectRequest> requests) {
        return requests.stream()
                .map(this::projectRequestToProject)
                .toList();
    }

    public List<ProjectResponse> projectListToProjectResponseList(List<Project> projects) {
        return projects.stream()
                .map(this::projectToProjectResponse)
                .toList();
    }
}
