package com.bugtracker.springboot.service;


import com.bugtracker.springboot.dto.ProjectDto;
import com.bugtracker.springboot.extras.ProjectIdException;
import com.bugtracker.springboot.models.Backlog;
import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.repository.BacklogRepo;
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

    @Autowired
    private BacklogRepo backlogRepo;

    public Project createProject(Project project)
    {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getProject_id()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getProject_id()!=null){
                project.setBacklog(backlogRepo.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

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

    public void deleteProject(String id)
    {
        Project project = projectRepo.findByProjectIdentifier(id.toUpperCase());
        if(project == null){
            throw  new  ProjectIdException("Cannot Project with ID '"+id+"'. This project does not exist");
        }
        projectRepo.deleteById(project.getProject_id());
    }

    //read project by id
    public Project byId(String id)
    {

        Project project = projectRepo.findByProjectIdentifier(id.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+id+"' does not exist");

        }
        return project;
    }




}
