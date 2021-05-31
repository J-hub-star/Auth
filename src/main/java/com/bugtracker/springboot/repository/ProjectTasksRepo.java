package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTasksRepo extends JpaRepository<ProjectTasks,Long> {
    List<ProjectTasks> findByProjectIdentifierOrderByPriority(String id);
    ProjectTasks findByProjectSequence(String sequence);
}
