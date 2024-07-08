package com.spring.utils;

import com.console_color.ConsoleColors;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.spring.model.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {
    private DatabaseHelper() {}

    private static Connection getConn() {
        Connection conn = null;
        String databaseConfigSource = "F:\\java\\source\\database_config.properties";
        Properties prop = new Properties();
        try (var input = new FileInputStream(databaseConfigSource)) {
            prop.load(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        String driver = prop.getProperty("driver");//com.mysql.cj.jdbc.Driver
        try {
            Class.forName(driver);
            String url = prop.getProperty("url"); // jdbc:mysql://192.168.1.1:3306
            String user = prop.getProperty("user");// root
            String password = prop.getProperty("password");// mypassword
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }

        return conn;
    }

    public boolean isConnectionClosed() {
        if (getConn() == null) {
            System.err.println("connection is null.");
            return true;
        }
        try {
            return getConn().isClosed();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
            return true;
        }
    }

    private void createDatabaseOrTable() {
        String sql = "CREATE DATABASE IF NOT EXISTS spring_core";
        try (var statement = getConn().createStatement()) {
            statement.execute(sql);
            sql = """
                    CREATE TABLE IF NOT EXISTS spring_core.employee (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50),
                        age INT,
                        address VARCHAR(250),
                        phone VARCHAR(15),
                        email VARCHAR(50),
                        salary DOUBLE
                    );
                    """;
            statement.execute(sql);
            statement.close();
            System.out.println(ConsoleColors.TEXT_BLUE + "Database and Table created." + ConsoleColors.TEXT_RESET);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Employee dto) {
        final String sql = """
                UPDATE spring_core.employee SET
                    name = ?,
                    age = ?,
                    address = ?,
                    phone = ?,
                    email = ?,
                    salary = ?
                WHERE id = ?;
                """;

        try (var ps = getConn().prepareStatement(sql)) {
            ps.setString(1, dto.getName());
            ps.setInt(2, dto.getAge());
            ps.setString(3, dto.getAddress());
            ps.setString(4, dto.getPhone());
            ps.setString(5, dto.getEmail());
            ps.setDouble(6, dto.getSalary());
            ps.setInt(7, dto.getId());
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println(ConsoleColors.TEXT_BLUE + "Employee updated." + ConsoleColors.TEXT_RESET);
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN + "Employee is already updated." + ConsoleColors.TEXT_RESET);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        final String sql = "DELETE FROM spring_core.employee WHERE id = ?;";

        try (var ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println(ConsoleColors.TEXT_RED + "Employee deleted." + ConsoleColors.TEXT_RESET);
            } else {
                System.out.println(ConsoleColors.TEXT_BLUE + "Employee is not exists." + ConsoleColors.TEXT_RESET);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
        }
    }

    public void insertEmployee(Employee dto) {
        final String sql = """
                INSERT INTO spring_core.employee 
                    ( name, age, address, phone, email, salary )
                    VALUES (?, ?, ?, ?, ?, ?);
                """;

        try (var ps = getConn().prepareStatement(sql)) {
            ps.setString(1, dto.getName());
            ps.setInt(2, dto.getAge());
            ps.setString(3, dto.getAddress());
            ps.setString(4, dto.getPhone());
            ps.setString(5, dto.getEmail());
            ps.setDouble(6, dto.getSalary());
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println(ConsoleColors.TEXT_GREEN + "Employee inserted." + ConsoleColors.TEXT_RESET);
            } else {
                System.out.println(ConsoleColors.TEXT_RED + "Something went wrong while insert a Employee,\nEmployee is not inserted." + ConsoleColors.TEXT_RESET);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        final String sql = "SELECT * FROM spring_core.employee;";
        List<Employee> employees = new ArrayList<>();

        try (var ps = getConn().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee dto = new Employee();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dto.setEmail(rs.getString("email"));
                dto.setSalary(rs.getDouble("salary"));
                employees.add(dto);
            }
            if (employees.isEmpty()) {
                System.out.println(ConsoleColors.TEXT_YELLOW + "Database is empty.." + ConsoleColors.TEXT_RESET);
            } else {
                System.out.println(ConsoleColors.TEXT_GREEN + "All employees have been successfully readed." + ConsoleColors.TEXT_RESET);
            }
            return employees;
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
            employees.clear();
            return employees;
        }
    }

    public Employee getEmployeeById(int id) {
        final String sql = "SELECT * FROM spring_core.employee WHERE id = ?;";
        Employee dto = null;

        try (var ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dto = new Employee();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dto.setEmail(rs.getString("email"));
                dto.setSalary(rs.getDouble("salary"));

                System.out.println(ConsoleColors.TEXT_GREEN + "Employee found. " + ConsoleColors.TEXT_RESET);
            } else {
                System.out.println(ConsoleColors.TEXT_YELLOW + "Employee not found. " + ConsoleColors.TEXT_RESET);
            }
        } catch (SQLException ex) {
            dto = null;
            System.err.println(ex.getClass().getName() + " " + ex.getMessage());
        }

        return dto;
    }
}