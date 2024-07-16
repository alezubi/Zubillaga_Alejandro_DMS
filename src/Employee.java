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
     Constructor for the Employee class.
     @param employeeID int the unique identifier for the employee
     @param firstName String the first name of the employee
     @param lastName String the last name of the employee
     @param email String the email address of the employee
     @param dateOfBirth Date the date of birth of the employee
     @param jobTitle String the job title of the employee
     @param department String the department the employee belongs to
     @param hireDate Date the date the employee was hired
     @param salary double the salary of the employee
     @param phoneNumber String the phone number of the employee
     @param address String the address of the employee
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

    /**
     * Returns the employee's unique identifier.
     * @return the employee ID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Returns the employee's first name.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the employee's last name.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the employee's email address.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the employee's date of birth.
     * @return the date of birth
     */
    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns the employee's job title.
     * @return the job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Returns the employee's department.
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the employee's hire date.
     * @return the hire date
     */
    public java.sql.Date getHireDate() {
        return hireDate;
    }

    /**
     * Returns the employee's salary.
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns the employee's phone number.
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the employee's address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the employee's first name.
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the employee's last name.
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the employee's email address.
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the employee's date of birth.
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the employee's job title.
     * @param jobTitle the new job title
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sets the employee's department.
     * @param department the new department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Sets the employee's hire date.
     * @param hireDate the new hire date
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Sets the employee's salary.
     * @param salary the new salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets the employee's phone number.
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the employee's address.
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}

