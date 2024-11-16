package classes;

import java.time.LocalDate;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String email;
    private int contactNo;
    private int jobID;
    private int hotelID;
    private float salary;
    private LocalDate hireDate;
    private LocalDate endDate;

    // Constructor
    public Employee(int employeeID, String employeeName, String email, int contactNo, int jobID, int hotelID, float salary, LocalDate hireDate, LocalDate endDate) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.email = email;
        this.contactNo = contactNo;
        this.jobID = jobID;
        this.hotelID = hotelID;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}