package com.code.service.impl;

import com.code.dto.request.ProjectRequest;
import com.code.dto.response.ProjectResponse;
import com.code.mapper.ProjectMapper;
import com.code.model.Project;
import com.code.repository.ProjectRepository;
import com.code.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Transactional
    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = projectMapper.projectRequestToProject(request);
        project = projectRepository.save(project);
        return projectMapper.projectToProjectResponse(project);
    }

    @Transactional
    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.getName());
        project = projectRepository.save(project);
        return projectMapper.projectToProjectResponse(project);
    }

    @Transactional
    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.projectToProjectResponse(project);
    }

    @Transactional
    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.projectListToProjectResponseList(projects);
    }

    @Transactional
    @Override
    public List<ProjectResponse> getProjectsByName(String name) {
        List<ProjectResponse> projectResponses = projectRepository
                .findByNameContaining(name)
                .stream()
                .map(projectMapper::projectToProjectResponse)
                .toList();
        return projectResponses;
    }
}
