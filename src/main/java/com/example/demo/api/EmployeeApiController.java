package com.example.demo.api;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("")
    public List<Employee> getEmployees() {
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return empRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        return empRepo.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee) {
        return empRepo.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee partialUpdate(@PathVariable("id") Long id, @Valid @RequestBody Employee patchEmployee) {
        Employee emp = empRepo.findById(id).get();

        if (patchEmployee.getEmail() != null) {
            emp.setEmail(patchEmployee.getEmail());
        }
        if (patchEmployee.getFirstName() != null) {
            emp.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getLastName() != null) {
            emp.setLastName(patchEmployee.getLastName());
        }
        return empRepo.save(emp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            empRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Doesn't exist");
        }
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        PageRequest pageAndSize = PageRequest.of(page, size);
        return empRepo.findAll(pageAndSize);
    }
}
