import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createEmployee(scanner);
                    break;
                case 2:
                    getEmployeeByID(scanner);
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    getAllEmployees();
                    break;
                case 6:
                    importEmployeesFromFile(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);



    }

    private static void displayMenu() {
        System.out.println("Employee Management System");
        System.out.println("1. Create Employee");
        System.out.println("2. Get Employee by ID");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Get All Employees");
        System.out.println("6. Import Employees from File");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void importEmployeesFromFile(Scanner scanner) {
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                if (employeeData.length == 11) {
                    int employeeID = Integer.parseInt(employeeData[0]);
                    String firstName = employeeData[1];
                    String lastName = employeeData[2];
                    String email = employeeData[3];
                    String dateOfBirth = employeeData[4];
                    String jobTitle = employeeData[5];
                    String department = employeeData[6];
                    String hireDate = employeeData[7];
                    double salary = Double.parseDouble(employeeData[8]);
                    String phoneNumber = employeeData[9];
                    String address = employeeData[10];

                    Employee employee = new Employee(
                            employeeID, firstName, lastName, email, dateOfBirth,
                            jobTitle, department, hireDate, salary, phoneNumber, address
                    );
                    employeeDAO.createEmployee(employee);
                    System.out.println("Employee added successfully: " + employee.getFirstName() + " " + employee.getLastName());
                } else {
                    System.out.println("Invalid employee data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid employee data format.");
        }
    }

    private static void createEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dateOfBirthStr = scanner.nextLine();
        String dateOfBirth = String.valueOf(Date.valueOf(dateOfBirthStr));

        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Hire Date (yyyy-MM-dd): ");
        String hireDateStr = scanner.nextLine();
        String hireDate = String.valueOf(Date.valueOf(hireDateStr));

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Employee employee = new Employee(employeeID, firstName, lastName, email, dateOfBirth, jobTitle, department, hireDate, salary, phoneNumber, address);
        employeeDAO.createEmployee(employee);
        System.out.println("Employee created successfully.");
    }

    private static void getEmployeeByID(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Employee employee = employeeDAO.getEmployeeByID(employeeID);
        if (employee != null) {
            System.out.println("Employee Details:");
            System.out.println("ID: " + employee.getEmployeeID());
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Date of Birth: " + employee.getDateOfBirth());
            System.out.println("Job Title: " + employee.getJobTitle());
            System.out.println("Department: " + employee.getDepartment());
            System.out.println("Hire Date: " + employee.getHireDate());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Phone Number: " + employee.getPhoneNumber());
            System.out.println("Address: " + employee.getAddress());
        } else {
            System.out.println("Employee not found.");
        }
    }
    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Employee employee = employeeDAO.getEmployeeByID(employeeID);
        if (employee != null) {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            String dateOfBirthStr = scanner.nextLine();
            Date dateOfBirth = Date.valueOf(dateOfBirthStr);

            System.out.print("Enter Job Title: ");
            String jobTitle = scanner.nextLine();

            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            System.out.print("Enter Hire Date (yyyy-MM-dd): ");
            String hireDateStr = scanner.nextLine();
            Date hireDate = Date.valueOf(hireDateStr);

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setDateOfBirth(dateOfBirth);
            employee.setJobTitle(jobTitle);
            employee.setDepartment(department);
            employee.setHireDate(hireDate);
            employee.setSalary(salary);
            employee.setPhoneNumber(phoneNumber);
            employee.setAddress(address);

            employeeDAO.updateEmployee(employee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        employeeDAO.deleteEmployee(employeeID);
        System.out.println("Employee deleted successfully.");
    }

    private static void getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("All Employees:");
            for (Employee employee : employees) {
                System.out.println("ID: " + employee.getEmployeeID());
                System.out.println("First Name: " + employee.getFirstName());
                System.out.println("Last Name: " + employee.getLastName());
                System.out.println("Email: " + employee.getEmail());
                System.out.println("Date of Birth: " + employee.getDateOfBirth());
                System.out.println("Job Title: " + employee.getJobTitle());
                System.out.println("Department: " + employee.getDepartment());
                System.out.println("Hire Date: " + employee.getHireDate());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("Phone Number: " + employee.getPhoneNumber());
                System.out.println("Address: " + employee.getAddress());
                System.out.println();
            }
        }
    }

}

