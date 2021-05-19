package com.example.trytwo.repository;

import com.example.trytwo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role,String>
{
    Optional<Role> findById(String id);

    List<Role> findByRole(String role);
}
