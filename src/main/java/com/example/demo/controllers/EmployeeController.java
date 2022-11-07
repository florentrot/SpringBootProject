package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empServ;


    @GetMapping("")
    public String displayEmployees(Model model) {
        List<Employee> employees = empServ.getAll();
        //my feature
        Employee employeez = new Employee("","","");
        model.addAttribute("employee",employeez);
        model.addAttribute("employees", employees);
        model.addAttribute("hidden", "visibility:hidden;");
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeFrom(Model model) {
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String addEmployee(@Validated Employee employee, Errors error) {

        if(error.hasErrors()) {
            return "employees/new-employee";
        }
        empServ.save(employee);
        return "redirect:/employees";

    }

    @GetMapping("/delete{id}")
    public String delete(@Param("id") Long id) {
        empServ.delete(id);
       return "redirect:/employees";
    }

    @GetMapping("/update{id}")
    public String update(@Param("id") Long id,Model model) {
        model.addAttribute("updateId", id);
        Employee employee = empServ.findById(id);
        model.addAttribute("employee",employee);
        List<Employee> employees = empServ.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/updateNow")
    public String updateEmployee(Employee employee) {
        empServ.save(employee);
        return "redirect:/employees";
    }
}
