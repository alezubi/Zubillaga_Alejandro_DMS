import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOTest {

    private EmployeeDAO employeeDAO;

    @Before
    public void setUp() {
        employeeDAO = new EmployeeDAO();
    }

    @After
    public void tearDown() {
        employeeDAO.deleteEmployeeByID(1);
        employeeDAO.deleteEmployeeByID(2);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", String.valueOf(LocalDate.of(1980, 1, 1)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2020, 1, 1)), 50000, "555-1234", "123 Main St.");
        employeeDAO.createEmployee(employee);

        Employee retrievedEmployee = employeeDAO.getEmployeeByID(1);
        assertNotNull(retrievedEmployee);
        assertEquals(employee.getEmployeeID(), retrievedEmployee.getEmployeeID());
        assertEquals(employee.getFirstName(), retrievedEmployee.getFirstName());
        assertEquals(employee.getLastName(), retrievedEmployee.getLastName());
        assertEquals(employee.getEmail(), retrievedEmployee.getEmail());
        assertEquals(employee.getDateOfBirth(), retrievedEmployee.getDateOfBirth());
        assertEquals(employee.getJobTitle(), retrievedEmployee.getJobTitle());
        assertEquals(employee.getDepartment(), retrievedEmployee.getDepartment());
        assertEquals(employee.getHireDate(), retrievedEmployee.getHireDate());
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.001);
        assertEquals(employee.getPhoneNumber(), retrievedEmployee.getPhoneNumber());
        assertEquals(employee.getAddress(), retrievedEmployee.getAddress());
    }

    @Test
    public void testGetEmployeeByID() {
        Employee employee = new Employee(2, "Jane", "Doe", "jane.doe@example.com", String.valueOf(LocalDate.of(1985, 5, 15)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2021, 2, 14)), 60000, "555-5678", "456 Elm St.");
        employeeDAO.createEmployee(employee);

        Employee retrievedEmployee = employeeDAO.getEmployeeByID(2);
        assertNotNull(retrievedEmployee);
        assertEquals(employee.getEmployeeID(), retrievedEmployee.getEmployeeID());
        assertEquals(employee.getFirstName(), retrievedEmployee.getFirstName());
        assertEquals(employee.getLastName(), retrievedEmployee.getLastName());
        assertEquals(employee.getEmail(), retrievedEmployee.getEmail());
        assertEquals(employee.getDateOfBirth(), retrievedEmployee.getDateOfBirth());
        assertEquals(employee.getJobTitle(), retrievedEmployee.getJobTitle());
        assertEquals(employee.getDepartment(), retrievedEmployee.getDepartment());
        assertEquals(employee.getHireDate(), retrievedEmployee.getHireDate());
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.001);
        assertEquals(employee.getPhoneNumber(), retrievedEmployee.getPhoneNumber());
        assertEquals(employee.getAddress(), retrievedEmployee.getAddress());
    }

    @Test
    public void testUpdateEmployee() {

        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", String.valueOf(LocalDate.of(1980, 1, 1)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2020, 1, 1)), 50000, "555-1234", "123 Main St.");
        employeeDAO.createEmployee(employee);


        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setEmail("jane.smith@example.com");
        employee.setDateOfBirth(Date.valueOf(LocalDate.of(1985, 5, 15)));
        employee.setJobTitle("Senior Software Engineer");
        employee.setDepartment("Research and Development");
        employee.setHireDate(Date.valueOf(LocalDate.of(2022, 1, 1)));
        employee.setSalary(60000);
        employee.setPhoneNumber("555-5678");
        employee.setAddress("456 Elm St.");


        employeeDAO.updateEmployee(employee);


        Employee retrievedEmployee = employeeDAO.getEmployeeByID(employee.getEmployeeID());
        assertNotNull(retrievedEmployee);
        assertEquals(employee.getEmployeeID(), retrievedEmployee.getEmployeeID());
        assertEquals(employee.getFirstName(), retrievedEmployee.getFirstName());
        assertEquals(employee.getLastName(), retrievedEmployee.getLastName());
        assertEquals(employee.getEmail(), retrievedEmployee.getEmail());
        assertEquals(employee.getDateOfBirth().toLocalDate(), retrievedEmployee.getDateOfBirth().toLocalDate());
        assertEquals(employee.getJobTitle(), retrievedEmployee.getJobTitle());
        assertEquals(employee.getDepartment(), retrievedEmployee.getDepartment());
        assertEquals(employee.getHireDate().toLocalDate(), retrievedEmployee.getHireDate().toLocalDate());
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.001);
        assertEquals(employee.getPhoneNumber(), retrievedEmployee.getPhoneNumber());
        assertEquals(employee.getAddress(), retrievedEmployee.getAddress());
    }

    @Test
    public void testDeleteEmployeeByID() {
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", String.valueOf(LocalDate.of(1980, 1, 1)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2020, 1, 1)), 50000, "555-1234", "123 Main St.");
        employeeDAO.createEmployee(employee);

        employeeDAO.deleteEmployeeByID(1);

        Employee retrievedEmployee = employeeDAO.getEmployeeByID(1);
        assertNull(retrievedEmployee);
    }

    @Test
    public void testDeleteEmployeeByEmail() {
        Employee employee1 = new Employee(1, "John", "Doe", "john.doe@example.com", String.valueOf(LocalDate.of(1980, 1, 1)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2020, 1, 1)), 50000, "555-1234", "123 Main St.");
        employeeDAO.createEmployee(employee1);

        Employee employee2 = new Employee(2, "Jane", "Doe", "jane.doe@example.com", String.valueOf(LocalDate.of(1985, 5, 15)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2021, 2, 14)), 60000, "555-5678", "456 Elm St.");
        employeeDAO.createEmployee(employee2);

        employeeDAO.deleteEmployeeByEmail("john.doe@example.com");

        Employee retrievedEmployee1 = employeeDAO.getEmployeeByID(1);
        assertNull(retrievedEmployee1);

        Employee retrievedEmployee2 = employeeDAO.getEmployeeByID(2);
        assertNotNull(retrievedEmployee2);
        assertEquals(employee2.getEmail(), retrievedEmployee2.getEmail());
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1, "John", "Doe", "john.doe@example.com", String.valueOf(LocalDate.of(1980, 1, 1)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2020, 1, 1)), 50000, "555-1234", "123 Main St.");
        employeeDAO.createEmployee(employee1);

        Employee employee2 = new Employee(2, "Jane", "Doe", "jane.doe@example.com", String.valueOf(LocalDate.of(1985, 5, 15)), "Software Engineer", "Engineering", String.valueOf(LocalDate.of(2021, 2, 14)), 60000, "555-5678", "456 Elm St.");
        employeeDAO.createEmployee(employee2);

        List<Employee> employees = employeeDAO.getAllEmployees();
        assertEquals(2, employees.size());

        Employee retrievedEmployee1 = employees.get(0);
        assertEquals(employee1.getEmployeeID(), retrievedEmployee1.getEmployeeID());
        assertEquals(employee1.getFirstName(), retrievedEmployee1.getFirstName());
        assertEquals(employee1.getLastName(), retrievedEmployee1.getLastName());
        assertEquals(employee1.getEmail(), retrievedEmployee1.getEmail());
        assertEquals(employee1.getDateOfBirth(), retrievedEmployee1.getDateOfBirth());
        assertEquals(employee1.getJobTitle(), retrievedEmployee1.getJobTitle());
        assertEquals(employee1.getDepartment(), retrievedEmployee1.getDepartment());
        assertEquals(employee1.getHireDate(), retrievedEmployee1.getHireDate());
        assertEquals(employee1.getSalary(), retrievedEmployee1.getSalary(), 0.001);
        assertEquals(employee1.getPhoneNumber(), retrievedEmployee1.getPhoneNumber());
        assertEquals(employee1.getAddress(), retrievedEmployee1.getAddress());

        Employee retrievedEmployee2 = employees.get(1);
        assertEquals(employee2.getEmployeeID(), retrievedEmployee2.getEmployeeID());
        assertEquals(employee2.getFirstName(), retrievedEmployee2.getFirstName());
        assertEquals(employee2.getLastName(), retrievedEmployee2.getLastName());
        assertEquals(employee2.getEmail(), retrievedEmployee2.getEmail());
        assertEquals(employee2.getDateOfBirth(), retrievedEmployee2.getDateOfBirth());
        assertEquals(employee2.getJobTitle(), retrievedEmployee2.getJobTitle());
        assertEquals(employee2.getDepartment(), retrievedEmployee2.getDepartment());
        assertEquals(employee2.getHireDate(), retrievedEmployee2.getHireDate());
        assertEquals(employee2.getSalary(), retrievedEmployee2.getSalary(), 0.001);
        assertEquals(employee2.getPhoneNumber(), retrievedEmployee2.getPhoneNumber());
        assertEquals(employee2.getAddress(), retrievedEmployee2.getAddress());
    }



}
