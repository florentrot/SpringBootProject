package com.example.demo.services;

import com.example.demo.dao.ProjectRepository;
import com.example.demo.dto.TimeChartData;
import com.example.demo.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository proRepo;

    public Project save(Project project) {
        return proRepo.save(project);
    }

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<TimeChartData> getTimeData() {
        return proRepo.getTimeData();
    }

}
