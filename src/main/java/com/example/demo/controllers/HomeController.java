package com.example.demo.controllers;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.ProjectRepository;
import com.example.demo.dto.EmployeeProject;
import com.example.demo.dto.ChartData;
import com.example.demo.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("")
public class HomeController {

    @Value("${versionEnv}") //this is from application-postgreSQL.properties
    private String ver;

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        Map<String, Objects> map = new HashMap<>();

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);

        // Wow! nice...
        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

        // My feature just to recap
        List<ChartData> projectData = proRepo.getProjectStatus();
        model.addAttribute("projectStage", projectData);

        //Convert projectData obj into a JSON structure for use in JS
        ObjectMapper objectMapper= new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [{"counted":1,"label":"COMPLETED"},{"counted":2,"label":"INPROGRESS"},{"counted":1,"label":"NOTSTARTED"}]
        model.addAttribute("projectStatusCnt", jsonString);

        return "main/home";
    }
}
