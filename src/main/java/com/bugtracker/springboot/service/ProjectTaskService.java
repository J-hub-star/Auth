package com.bugtracker.springboot.service;

import com.bugtracker.springboot.models.Backlog;
import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.models.ProjectTasks;
import com.bugtracker.springboot.repository.BacklogRepo;
import com.bugtracker.springboot.repository.ProjectRepo;
import com.bugtracker.springboot.repository.ProjectTasksRepo;
import com.bugtracker.springboot.utility.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepo backlogRepository;

    @Autowired
    private ProjectTasksRepo projectTaskRepository;

    @Autowired
    private ProjectRepo projectRepository;


    public ProjectTasks addProjectTask(String projectIdentifier, ProjectTasks projectTask){

        try {
            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the bl to pt
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;

            backlog.setPTSequence(BacklogSequence);

            //Add Sequence to Project Task
            projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITIAL priority when priority null

            //INITIAL status when status is null
            if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
                projectTask.setStatus("TO_DO");
            }

            if(projectTask.getPriority()==null){ //In the future we need projectTask.getPriority()== 0 to handle the form
                projectTask.setPriority(3);
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project not Found");
        }

    }

    public Iterable<ProjectTasks>findBacklogById(String id){

        Project project = projectRepository.findByProjectIdentifier(id);

        if(project==null){
            throw new ProjectNotFoundException("Project with ID: '"+id+"' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTasks findPTByProjectSequence(String backlog_id, String pt_id){
        //make sure we are searching on an existing backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog==null){
            throw new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist");
        }
        //make sure that our task exists
        ProjectTasks projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' not found");
        }
        //make sure that the backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id);
        }
        return projectTask;
    }

    public ProjectTasks updateByProjectSequence(ProjectTasks updatedTask, String backlog_id, String pt_id){
        ProjectTasks projectTask = findPTByProjectSequence(backlog_id, pt_id);
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }


    public void deletePTByProjectSequence(String backlog_id, String pt_id){
        ProjectTasks projectTask = findPTByProjectSequence(backlog_id, pt_id);
        Backlog backlog = projectTask.getBacklog();
        List<ProjectTasks> pts = backlog.getProjectTasks();
        pts.remove(projectTask);
        backlogRepository.save(backlog);
        projectTaskRepository.delete(projectTask);
    }
}