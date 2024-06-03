import java.sql.Date;
/**
 * Author: Alejandro Zubillaga
 * Course: 202430-CEN-3024C-31950
 * Date: Jun 1, 2024
 * Software Development I
 *
 * Employee Class
 * This class represents an employee in the HR management system. It contains the employee's personal and employment information,
 * such as employee ID, name, email, date of birth, job title, department, hire date, salary, phone number, and address.
 * This class is used throughout the HR management system to store and manipulate employee data.
 */

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private java.sql.Date dateOfBirth;
    private String jobTitle;
    private String department;
    private java.sql.Date hireDate;
    private double salary;
    private String phoneNumber;
    private String address;

    /**
     * Constructor for the Employee class.
     *
     *  employeeID    the unique identifier for the employee
     * firstName     the first name of the employee
     * lastName      the last name of the employee
     * email         the email address of the employee
     * dateOfBirth   the date of birth of the employee
     * jobTitle      the job title of the employee
     * department    the department the employee belongs to
     * hireDate      the date the employee was hired
     * salary        the salary of the employee
     * phoneNumber   the phone number of the employee
     * address       the address of the employee
     */

    public Employee(int employeeID, String firstName, String lastName, String email, String dateOfBirth, String jobTitle, String department, String hireDate, double salary, String phoneNumber, String address) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = Date.valueOf(dateOfBirth);
        this.jobTitle = jobTitle;
        this.department = department;
        this.hireDate = Date.valueOf(hireDate);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public java.sql.Date getHireDate() {
        return hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

