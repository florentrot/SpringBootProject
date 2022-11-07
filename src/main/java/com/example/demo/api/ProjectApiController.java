package com.example.demo.api;

import com.example.demo.dao.ProjectRepository;
import com.example.demo.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository proRepo;

    @GetMapping("")
    public List<Project> getProjects() {
        return proRepo.findAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable("{id}") Long id) {
        return proRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project saveProject(@RequestBody Project project) {
        return proRepo.save(project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project) {
        return proRepo.save(project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project partialUpdate(@PathVariable("id") Long id, @RequestBody Project patchProject){
        Project project = proRepo.findById(id).get();

        if(patchProject.getName()!=null) {
            project.setName(patchProject.getName());
        }
        if(patchProject.getStage()!=null) {
            project.setStage(patchProject.getStage());
        }
        if(patchProject.getDescription()!=null) {
            project.setDescription(patchProject.getDescription());
        }
        return proRepo.save(project);
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            proRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Doesn't exist");
        }
    }
}
