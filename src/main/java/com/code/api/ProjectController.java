package com.code.api;

import com.code.dto.request.ProjectRequest;
import com.code.dto.response.ProjectResponse;
import com.code.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody
                                                         ProjectRequest projectRequest) {
        ProjectResponse projectResponse = projectService.createProject(projectRequest);
        return new ResponseEntity<>(projectResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody
    ProjectRequest projectRequest) {
        ProjectResponse projectResponse = projectService.updateProject(id, projectRequest);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/projects
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/projects?name=[variable]
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponse>> searchProjectsByNameContining(@RequestParam String name) {
        List<ProjectResponse> projects = projectService.getProjectsByName(name);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
