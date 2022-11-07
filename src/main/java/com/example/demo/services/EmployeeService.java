package com.example.demo.services;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dto.EmployeeProject;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    public void delete(long id) {
        empRepo.deleteById(id);
        System.out.println("was deleted");
    }

    public Employee findById(long id) {
        return empRepo.findById(id).get();
    }


}
