package com.example.demo.controllers;

import com.example.demo.dto.TimeChartData;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Project;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService proServ;

    @Autowired
    EmployeeService empServ;

    @GetMapping("")
    public String displayProjects(Model model) {
        List<Project> projects = proServ.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project aProject = new Project();
        List<Employee> employees = empServ.getAll();
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(@Validated Project project, Errors errors) {

        if(errors.hasErrors()){
            return "redirect:/projects/new";
        }
        proServ.save(project);
        //prevent duplicate submissions with this redirect
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {

        List<TimeChartData> timeLineData = proServ.getTimeData();

        ObjectMapper om = new ObjectMapper();
        String jsonTimelineString= om.writeValueAsString(timeLineData);
        model.addAttribute("projectTimeList", jsonTimelineString);
        return "projects/project-timelines";
    }
}
