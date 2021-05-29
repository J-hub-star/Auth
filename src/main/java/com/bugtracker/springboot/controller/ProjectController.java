package com.bugtracker.springboot.controller;


import com.bugtracker.springboot.dto.ProjectDto;
import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    //Create a Project
    @PostMapping("/create")
    public Project createProject(@RequestBody ProjectDto projectDto)
    {
        return projectService.createProject(projectDto);
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
