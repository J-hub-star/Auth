package com.bugtracker.springboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bug
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bug_id;
    @ManyToOne
    private Project project;
    @Enumerated(value = EnumType.STRING)
    private Priority priority;
}
