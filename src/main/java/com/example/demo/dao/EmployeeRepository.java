package com.example.demo.dao;

import com.example.demo.dto.EmployeeProject;
import com.example.demo.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Override
    public List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(pe.employee_id) AS projectCount " +
            "FROM employee e LEFT JOIN project_employee pe ON pe.employee_id =e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmail(String email);

}
