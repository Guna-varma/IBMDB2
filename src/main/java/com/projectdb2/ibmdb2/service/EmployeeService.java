package com.projectdb2.ibmdb2.service;


import com.projectdb2.ibmdb2.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectdb2.ibmdb2.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Transactional
    public Employee addEmployee(Employee employee) {
        // Validation for required fields
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        } else if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        } else if (employee.getEmployeeId() == null || employee.getEmployeeId().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }

        Employee e =  new Employee();
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setEmployeeId(employee.getEmployeeId());
        e.setBloodGroup(employee.getBloodGroup());
        return repo.save(e);
    }

    public List<Employee> getAllEmployee() {
        return repo.findAll();
    }

    public Optional<Employee> getEmpById(Long id) {
        return repo.findById(id);
    }

    public Boolean deleteEmp(Long id) {
        repo.deleteById(id);
        return true;
    }

    @Transactional
    public Employee updateEmployee( Employee newEmployeeData) {
        Optional<Employee> existingEmployeeOptional = repo.findById(newEmployeeData.getId());

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Update only non-null fields
            if (newEmployeeData.getEmployeeId() != null) {
                existingEmployee.setEmployeeId(newEmployeeData.getEmployeeId());
            }
            if (newEmployeeData.getFirstName() != null) {
                existingEmployee.setFirstName(newEmployeeData.getFirstName());
            }
            if (newEmployeeData.getLastName() != null) {
                existingEmployee.setLastName(newEmployeeData.getLastName());
            }
            if (newEmployeeData.getBloodGroup() != null) {
                existingEmployee.setBloodGroup(newEmployeeData.getBloodGroup());
            }

            // Save and return the updated employee
            return repo.save(existingEmployee);
        } else {
            throw new EntityNotFoundException("Employee not found with ID: " + newEmployeeData.getId());
        }
    }
}
