package com.projectdb2.ibmdb2.controller;

import com.projectdb2.ibmdb2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projectdb2.ibmdb2.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empl")
@CrossOrigin(origins = "http://localhot:3000")
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

//    @DeleteMapping("/delete/{id}")
//    private Boolean deleteEmployee(@PathVariable Long id){
//        return service.deleteEmp(id);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean deleted = service.deleteEmp(id);
        if (deleted) {
            return ResponseEntity.ok("Employee deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
    }

    @PutMapping("/update")
    public Employee updEmployee(@RequestBody Employee newEmployeeData) {
        return service.updateEmployee(newEmployeeData);
    }
}
