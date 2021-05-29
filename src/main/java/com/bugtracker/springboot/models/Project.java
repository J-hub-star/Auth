package com.bugtracker.springboot.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
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
    @NotNull(message = "Repository Link is required")
    private String github_link;
    @NotNull(message = "Identifier has to be unique")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    //bidirectional relational model
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<Bug> bugs;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<Bug> todos;
    private Date startedAt;
    private Date createdOn;

}
