package com.crud.crudapplication.service.impl;

import com.crud.crudapplication.exception.ResourceNotFoundException;
import com.crud.crudapplication.model.Employee;
import com.crud.crudapplication.repository.EmployeeRepository;
import com.crud.crudapplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    //@Autowired not required if we have only one constructor
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));


    }

    @Override
    public Employee updateEmployeeById(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
      employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
      employeeRepository.deleteById(id);
    }

}
