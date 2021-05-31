package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepo extends JpaRepository<Backlog,Long> {
      Backlog findByProjectIdentifier(String Identofier);
}
