package com.example.trytwo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends Generic
{
    @Indexed(direction = IndexDirection.DESCENDING,unique = true)
    private String username;
    private String token;
    private String password;
    @Indexed(direction = IndexDirection.DESCENDING,unique = true)
    private String email;
    private List<String> role;

    public User(){super();}
}
