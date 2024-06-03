import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Alejandro Zubillaga
 * Course: 202430-CEN-3024C-31950
 * Date: Jun 1, 2024
 *
 * EmployeeDAO Class
 * This class is responsible for interacting with the HR database to perform CRUD (Create, Read, Update, Delete) operations on employee data.
 * It establishes a connection to the MySQL database, and provides methods to create, retrieve, update, and delete employee records.
 * The EmployeeDAO class is used by the main application to manage employee information.
 */

public class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hr_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ale.zubi99";

    private Connection connection;

    /**
     * Constructor for the EmployeeDAO class.
     * Establishes a connection to the HR database using the provided URL, username, and password.
     */
    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new employee record in the database.
     *
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
     * Retrieves an employee record from the database based on the employee's ID.
     *
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
     * Updates an existing employee record in the database.
     *
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
     * Deletes an employee record from the database based on the employee's ID.
     *
     * @param employeeID the unique identifier of the employee to be deleted
     */
    public void deleteEmployee(int employeeID) {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all employees from the database.
     *
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
     * Retrieves a list of employees based on the specified filter criteria.
     *
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