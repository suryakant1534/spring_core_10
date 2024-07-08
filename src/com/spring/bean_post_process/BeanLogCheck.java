package com.spring.bean_post_process;

import com.console_color.ConsoleColors;
import com.spring.controller.Controller;
import com.spring.model.Employee;
import com.spring.service.Service;
import com.spring.utils.DatabaseHelper;

public class BeanLogCheck {
    static public void afterInitDatabaseHelper(Object bean) {
        if (bean instanceof DatabaseHelper && bean.getClass().equals(DatabaseHelper.class)) {
            DatabaseHelper helper = (DatabaseHelper) bean;
            if (helper == null) {
                System.err.println("Database connection is null.");
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN + "DatabaseHelper is connected." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void beforeInitDatabaseHelper(Object bean) {
        if (bean instanceof DatabaseHelper && bean.getClass().equals(DatabaseHelper.class)) {
            DatabaseHelper helper = (DatabaseHelper) bean;
            if (helper == null) {
                System.err.println("DatabaseHelper is not initialized.");
            } else {
                System.out.println(ConsoleColors.TEXT_YELLOW + "DatabaseHelper is initialized." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void afterInitEmployee(Object bean) {
        if (bean instanceof Employee && bean.getClass().equals(Employee.class)) {
            Employee employee = (Employee) bean;
            if (employee == null) {
                System.err.println("Employee is not initialized after init.");
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN +"Employee is initialized ater init." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void beforeInitEmployee(Object bean) {
        if (bean instanceof Employee && bean.getClass().equals(Employee.class)) {
            Employee employee = (Employee) bean;
            if (employee == null) {
                System.err.println("Employee is not initialized before init.");
            } else {
                System.out.println(ConsoleColors.TEXT_YELLOW + "Employee is initialized before init." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void beforeInitService(Object bean) {
        if (bean instanceof Service && bean.getClass().equals(Service.class)) {
            Service service = (Service) bean;
            if (service == null) {
                System.err.println("Service is not initialized before init.");
            } else {
                System.out.println(ConsoleColors.TEXT_YELLOW + "Service is initialized before init." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void afterInitService(Object bean) {
        if (bean instanceof Service && bean.getClass().equals(Service.class)) {
            Service service = (Service) bean;
            if (service == null) {
                System.err.println("Service is not initialized after init.");
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN + "Service is initialized after init." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void beforeInitController(Object bean) {
        if (bean instanceof Controller && bean.getClass().equals(Controller.class)) {
            Controller controller = (Controller) bean;
            if (controller == null) {
                System.err.println("Controller is not initialized before init.");
            } else {
                System.out.println(ConsoleColors.TEXT_YELLOW + "Controller is initialized before init." + ConsoleColors.TEXT_RESET);
            }
        }
    }

    static public void afterInitController(Object bean) {
        if (bean instanceof Controller && bean.getClass().equals(Controller.class)) {
            Controller controller = (Controller) bean;
            if (controller == null) {
                System.err.println("Controller is not initialized after init.");
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN + "Controller is initialized after init." + ConsoleColors.TEXT_RESET);
            }
        }
    }
}
