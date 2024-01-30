package com.code.service;

import com.code.dto.request.ProjectRequest;
import com.code.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void deleteProject(Long id);

    ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects();

    List<ProjectResponse> getProjectsByName(String name);

}
