package com.example.trytwo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<Role> role;

    public User(){super();}
}
