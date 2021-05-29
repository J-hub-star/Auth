package com.bugtracker.springboot.service;

import com.bugtracker.springboot.models.Bug;
import com.bugtracker.springboot.repository.BugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService
{
    @Autowired
    private BugRepo bugRepo;
    //find all the bugs ofa given project
    public List<Bug> allBugs()
    {
        return bugRepo.findAll();
    }

}
