package com.projectdb2.ibmdb2.controller;

import com.projectdb2.ibmdb2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.projectdb2.ibmdb2.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empl")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/add")
    private Employee addEmployee(@RequestBody Employee Employee){
        return service.addEmployee(Employee);
    }

    @GetMapping("/getAll")
    private List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/get/{id}")
    private Optional<Employee> getEmpById(@PathVariable Long id){
        return service.getEmpById(id);
    }

    @DeleteMapping("/delete/{id}")
    private Boolean deleteEmployee(@PathVariable Long id){
        return service.deleteEmp(id);
    }

}
