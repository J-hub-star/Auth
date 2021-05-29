package com.bugtracker.springboot.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long project_id;
    private String project_name;
    private String github_link;
    //bidirectional relational model
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<Bug> bugs;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<Bug> todos;
}
