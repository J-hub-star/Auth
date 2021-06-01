package com.bugtracker.springboot.repository;

import com.bugtracker.springboot.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends CrudRepository<User, Long> {


    User findByUsername(String username);
    User getById(Long id);
}
