package com.spring.model;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;
    private String email;
    private double salary;

    public Employee() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(int id, String name, int age, String address, String phone, String email, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
    }
    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Employee{" + '\n' +
                "   id=" + id + ",\n" +
                "   name='" + name + '\'' + ",\n"+
                "   age=" + age + ",\n"+
                "   address='" + address + '\'' + ",\n"+
                "   phone='" + phone + '\'' + ",\n"+
                "   email='" + email + '\'' + ",\n"+
                "   salary=" + salary + '\n' +
                '}';
    }

    public double getSalary() {
        return salary;
    }
}
