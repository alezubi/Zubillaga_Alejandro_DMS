import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Alejandro Zubillaga
 * Course: 202430-CEN-3024C-31950
 * Date: Jun 1, 2024
 * Software Development I
 *
 * EmployeeDAO Class
 * This class is responsible for interacting with the HR database to perform CRUD (Create, Read, Update, Delete) operations on employee data.
 * It establishes a connection to the MySQL database, and provides methods to create, retrieve, update, and delete employee records.
 * The EmployeeDAO class is used by the main application to manage employee information.
 */

public class EmployeeDAO {
    private Connection connection;

    /**
     * Constructor for the EmployeeDAO class.
     * Establishes a connection to the HR database using the provided URL, username, and password.
     * @param dbUrl the database URL
     * @param dbUser the database user
     * @param dbPassword the database password
     */
    public EmployeeDAO(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * createEmployee: Creates a new employee record in the database.
     * This method does not return anything (void)
     * @param employee the Employee object containing the employee's information
     */
    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (EmployeeID, FirstName, LastName, Email, DateOfBirth, JobTitle, Department, HireDate, Salary, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getEmail());
            statement.setDate(5, employee.getDateOfBirth());
            statement.setString(6, employee.getJobTitle());
            statement.setString(7, employee.getDepartment());
            statement.setDate(8, employee.getHireDate());
            statement.setDouble(9, employee.getSalary());
            statement.setString(10, employee.getPhoneNumber());
            statement.setString(11, employee.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * getEmployeeByID: Retrieves an employee record from the database based on the employee's ID.
     * This method returns an Employee object if a matching record is found in the database, or null if no record is found.
     * @param employeeID the unique identifier of the employee
     * @return the Employee object containing the employee's information, or null if the employee is not found
     */
    public Employee getEmployeeByID(int employeeID) {
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee(
                            resultSet.getInt("EmployeeID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("JobTitle"),
                            resultSet.getString("Department"),
                            resultSet.getString("HireDate"),
                            resultSet.getDouble("Salary"),
                            resultSet.getString("PhoneNumber"),
                            resultSet.getString("Address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * updateEmployee: Updates an existing employee record in the database.
     * This method does not return anything (void)
     * @param employee the Employee object containing the updated employee information
     */
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employees SET FirstName = ?, LastName = ?, Email = ?, DateOfBirth = ?, JobTitle = ?, Department = ?, HireDate = ?, Salary = ?, PhoneNumber = ?, Address = ? WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setDate(4, employee.getDateOfBirth());
            statement.setString(5, employee.getJobTitle());
            statement.setString(6, employee.getDepartment());
            statement.setDate(7, employee.getHireDate());
            statement.setDouble(8, employee.getSalary());
            statement.setString(9, employee.getPhoneNumber());
            statement.setString(10, employee.getAddress());
            statement.setInt(11, employee.getEmployeeID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deleteEmployeeByID: Deletes an employee record from the database based on the employee's ID.
     * This method does not return anything (void)
     * @param employeeID the unique identifier of the employee to be deleted
     */
    public void deleteEmployeeByID(int employeeID) {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }
    /**
     * deleteEmployeeByEmail: Deletes an employee record from the database based on the employee's ID.
     * This method does not return anything (void)
     * the unique identifier of the employee to be deleted
     */
    public void deleteEmployeeByEmail(String email) {
        String sql = "DELETE FROM Employees WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No employee found with the given email.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    /**
     * getAllEmployees: Retrieves a list of all employees from the database.
     * This method returns a List<Employee> containing all the employee records retrieved from the database.
     * @return a list of Employee objects containing all employee records
     */
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("JobTitle"),
                        resultSet.getString("Department"),
                        resultSet.getString("HireDate"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Address")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * getEmployeesByFilter: Retrieves a list of employees based on the specified filter criteria.
     * The method returns a List<Employee> containing the employee records that match the specified filter criteria. If no records are found, the method returns an empty list.
     * @param department the department to filter by (can be empty for no filter)
     * @param jobTitle the job title to filter by (can be empty for no filter)
     * @param minSalary the minimum salary to filter by (0 for no minimum)
     * @param maxSalary the maximum salary to filter by (Double.MAX_VALUE for no maximum)
     * @return a list of Employee objects that match the specified filter criteria
     */
    public List<Employee> getEmployeesByFilter(String department, String jobTitle, double minSalary, double maxSalary) {
        List<Employee> filteredEmployees = new ArrayList<>();
        String sql = "SELECT * FROM Employees WHERE 1 = 1";

        if (!department.isEmpty()) {
            sql += " AND Department = ?";
        }

        if (!jobTitle.isEmpty()) {
            sql += " AND JobTitle = ?";
        }

        sql += " AND Salary BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            if (!department.isEmpty()) {
                statement.setString(paramIndex++, department);
            }
            if (!jobTitle.isEmpty()) {
                statement.setString(paramIndex++, jobTitle);
            }
            statement.setDouble(paramIndex++, minSalary);
            statement.setDouble(paramIndex, maxSalary);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getInt("EmployeeID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("JobTitle"),
                            resultSet.getString("Department"),
                            resultSet.getString("HireDate"),
                            resultSet.getDouble("Salary"),
                            resultSet.getString("PhoneNumber"),
                            resultSet.getString("Address")
                    );
                    filteredEmployees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredEmployees;
    }
}

