package model.gho;

import lombok.Data;

@Data
public class Employee {

    private String firstName;
    private String lastName;
    private double salary;
    private int departmentId;

    public Employee() {

    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, double salary, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
