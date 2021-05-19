package com.example.trytwo.repository;

import com.example.trytwo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends MongoRepository<User, String>
{

    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
    List<User> findByUsernameOrEmail(String s);
}
