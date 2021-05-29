package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.Project;
import com.bugtracker.springboot.models.ToDo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo,Long> {
}
