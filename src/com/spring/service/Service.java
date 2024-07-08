package com.spring.service;

import com.spring.dto.EmployeeDto;
import com.spring.model.Employee;
import com.spring.utils.DatabaseHelper;

import java.util.List;

public class Service {
    private DatabaseHelper databaseHelper;
    private Service(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    private Employee getEmployee(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());

        return employee;
    }

    public boolean isConnectionClosed() {
        return databaseHelper.isConnectionClosed();
    }

   public void insertEmployee(EmployeeDto dto) {
        Employee employee = getEmployee(dto);
        databaseHelper.insertEmployee(employee);
   }

   public void updateEmployee(EmployeeDto dto) {
        Employee employee = getEmployee(dto);
        databaseHelper.updateEmployee(employee);
   }
   public void deleteEmployee(int id) {
        databaseHelper.deleteEmployee(id);
   }
   public List<Employee> getEmployees() {
        return databaseHelper.getAllEmployees();
   }

   public Employee getEmployeeById(int id) {
        return databaseHelper.getEmployeeById(id);
   }
}
