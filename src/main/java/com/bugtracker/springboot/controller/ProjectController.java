package com.bugtracker.springboot.controller;


import com.bugtracker.springboot.dto.ProjectDto;
import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.service.MapValidationErrorService;
import com.bugtracker.springboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //Create a Project
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project projectDto, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Project project1 = projectService.createProject(projectDto);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }
    // get all projects
    @GetMapping
    public List<Project> allProjects(){ return projectService.findAll();}
    //get by id
    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id)
    {
        return projectService.byId(id);
    }
    //delete a project



}
