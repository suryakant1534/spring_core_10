package com.spring.dto;

public class EmployeeDto {
    final private String name;
    final private int age;
    final private String address;
    final private String phone;
    final private String email;
    final private double salary;

    public EmployeeDto( String name, int age, String address, String phone, String email, double sallary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.salary = sallary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
