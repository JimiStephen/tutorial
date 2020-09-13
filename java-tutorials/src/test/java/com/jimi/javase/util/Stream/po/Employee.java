package com.jimi.javase.util.Stream.po;



public class Employee {

    public Employee(Department department, Integer salary) {
        this.department = department;
        this.salary = salary;
    }

    private Department department;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    private Integer salary;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
