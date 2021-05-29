package com.bugtracker.springboot.service;


import com.bugtracker.springboot.dto.ProjectDto;
import com.bugtracker.springboot.extras.ProjectIdException;
import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService
{
    @Autowired
    private ProjectRepo projectRepo;

    public Project createProject(Project project)
    {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepo.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }


    }

    //Find all the projects
    public List<Project> findAll()
    {
        return projectRepo.findAll();
    }

    //update a project
    public Project updateProject(ProjectDto projectDto){
        Project project = new Project();
        project.setProject_name(projectDto.getName());
        project.setGithub_link(projectDto.getLink());
        return projectRepo.save(project);
    }

    public void deleteProject(Long id)
    {
        projectRepo.deleteById(id);
    }

    //read project by id
    public Project byId(Long id)
    {
        Optional<Project> project = projectRepo.findById(id);
        if(!project.isPresent()){
            throw new RuntimeException("Project isn't present");
        }
        return project.get();
    }




}
