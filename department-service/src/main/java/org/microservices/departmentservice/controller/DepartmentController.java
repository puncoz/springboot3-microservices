package org.microservices.departmentservice.controller;

import org.microservices.departmentservice.client.EmployeeClient;
import org.microservices.departmentservice.model.Department;
import org.microservices.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department List");

        return repository.findAll();
    }

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department to add: {}", department);

        return repository.addDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find by id: {}", id);

        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Find all departments with employees");

        List<Department> departments = repository.findAll();

        departments.forEach(department -> {
            department.setEmployees(employeeClient.findByDepartment(department.getId()));
        });

        return departments;
    }
}
