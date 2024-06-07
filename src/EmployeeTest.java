import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;

public class EmployeeTest {

    @Test
    public void testEmployee() {
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", "1980-01-01", "Software Engineer", "Engineering", "2020-01-01", 50000, "555-1234", "123 Main St.");

        // Test getters
        assertEquals(1, employee.getEmployeeID());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals(Date.valueOf("1980-01-01"), employee.getDateOfBirth());
        assertEquals("Software Engineer", employee.getJobTitle());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals(Date.valueOf("2020-01-01"), employee.getHireDate());
        assertEquals(50000, employee.getSalary(), 0.001);
        assertEquals("555-1234", employee.getPhoneNumber());
        assertEquals("123 Main St.", employee.getAddress());

        // Test setters
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setEmail("jane.smith@example.com");
        employee.setDateOfBirth(Date.valueOf("1990-01-01"));
        employee.setJobTitle("Senior Software Engineer");
        employee.setDepartment("Research and Development");
        employee.setHireDate(Date.valueOf("2021-01-01"));
        employee.setSalary(60000);
        employee.setPhoneNumber("555-5678");
        employee.setAddress("456 Elm St.");

        assertEquals(1, employee.getEmployeeID());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());
        assertEquals("jane.smith@example.com", employee.getEmail());
        assertEquals(Date.valueOf("1990-01-01"), employee.getDateOfBirth());
        assertEquals("Senior Software Engineer", employee.getJobTitle());
        assertEquals("Research and Development", employee.getDepartment());
        assertEquals(Date.valueOf("2021-01-01"), employee.getHireDate());
        assertEquals(60000, employee.getSalary(), 0.001);
        assertEquals("555-5678", employee.getPhoneNumber());
        assertEquals("456 Elm St.", employee.getAddress());
    }
}