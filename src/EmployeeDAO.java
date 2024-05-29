import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hr_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ale.zubi99";

    private Connection connection;

    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void deleteEmployee(int employeeID) {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
}

