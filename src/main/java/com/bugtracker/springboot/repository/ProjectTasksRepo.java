package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTasksRepo extends JpaRepository<ProjectTasks,Long> {
}
