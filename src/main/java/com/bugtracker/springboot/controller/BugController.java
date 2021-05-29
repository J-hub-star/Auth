package com.bugtracker.springboot.controller;

import com.bugtracker.springboot.models.Bug;
import com.bugtracker.springboot.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController
{
    @Autowired
    private BugService bugService;
    //display all the bugs in the system
    @GetMapping
    public List<Bug> displayAll()
    {
        return bugService.allBugs();
    }
    //display by project

}
