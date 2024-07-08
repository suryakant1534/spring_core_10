package com.spring.controller;

import com.spring.dto.EmployeeDto;
import com.spring.model.Employee;
import com.spring.service.Service;

import java.util.List;

public class Controller {
    private Service service;
    private Controller(Service service) {
        this.service = service;
    }

    public boolean isConnectionClosed() {
        return service.isConnectionClosed();
    }

    public void insertEmployee(EmployeeDto dto) {
        service.insertEmployee(dto);
    }

    public void updateEmployee(EmployeeDto dto) {
        service.updateEmployee(dto);
    }

    public void deleteEmployee(int id) {
        service.deleteEmployee(id);
    }

    public List<Employee> getAllEmployees() {
        return service.getEmployees();
    }

    public Employee getEmployeeById(int id) {
        return service.getEmployeeById(id);
    }
}
