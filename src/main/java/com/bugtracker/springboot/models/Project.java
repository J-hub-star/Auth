package com.bugtracker.springboot.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(updatable = false)
    private String projectIdentifier;
    private Date startedAt;
    private Date createdOn;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

}
