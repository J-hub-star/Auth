package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long>
{
    Project findByProjectIdentifier(String projectId);

    @Override
    List<Project> findAll();
}
