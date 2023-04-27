package org.microservices.departmentservice.controller;

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
}
