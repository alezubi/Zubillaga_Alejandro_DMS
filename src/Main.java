import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Alejandro Zubillaga
 * Course: 202430-CEN-3024C-31950
 * Date: Jun 1, 2024
 * Software Development I
 *
 * Main Class
 *
 * This is the main class of the Employee Management System. It provides the user interface and handles the user's interactions with the system.
 * The main class creates an instance of the EmployeeDAO class, which is responsible for interacting with the HR database to perform CRUD operations on employee data.
 * The main class displays a menu of options to the user, and calls the appropriate methods in the EmployeeDAO class based on the user's selection.
 */
public class Main {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    /**
     *
     * The main entry point of the Employee Management System.
     */
    public static void main(String[] args) {

        /** main(String[] args) This method does not return anything (void).
         *  This is an array of command-line arguments passed to the program when it is executed.
         */
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
                    generateCustomReport(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);



    }

    /**
     * Displays the main menu of the Employee Management System.
     * displayMenu(): This method displays the main menu of the Employee Management System, showing the available options.
     * This method does not take any arguments.
     */
    private static void displayMenu() {
        System.out.println("Employee Management System");
        System.out.println("1. Create Employee");
        System.out.println("2. Get Employee by ID");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Get All Employees");
        System.out.println("6. Import Employees from File");
        System.out.println("7. Generate Custom Report");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Imports employee data from a file and adds the employees to the HR database.
     * importEmployeesFromFile(Scanner scanner): This method allows the user to import employee data from a file and add the employees to the HR database.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void).
     */
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

    /**
     * Creates a new employee record in the HR database.
     * createEmployee(Scanner scanner): This method prompts the user to enter employee details and creates a new employee record in the HR database.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void).
     */
    private static void createEmployee(Scanner scanner) {
        int employeeID;
        while (true) {
            System.out.print("Enter Employee ID: ");
            if (scanner.hasNextInt()) {
                employeeID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        String firstName;
        while (true) {
            System.out.print("Enter First Name: ");
            if (scanner.hasNextLine()) {
                firstName = scanner.nextLine();
                if (!firstName.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("First name cannot be empty.");
                }
            }
        }

        String lastName;
        while (true) {
            System.out.print("Enter Last Name: ");
            if (scanner.hasNextLine()) {
                lastName = scanner.nextLine();
                if (!lastName.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Last name cannot be empty.");
                }
            }
        }

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            if (scanner.hasNextLine()) {
                email = scanner.nextLine();
                if (!email.trim().isEmpty() && email.contains("@")) {
                    break;
                } else {
                    System.out.println("Invalid email. Please enter a valid email address.");
                }
            }
        }

        String dateOfBirthStr;
        while (true) {
            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            if (scanner.hasNextLine()) {
                dateOfBirthStr = scanner.nextLine();
                try {
                    Date.valueOf(dateOfBirthStr);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            }
        }
        String dateOfBirth = String.valueOf(Date.valueOf(dateOfBirthStr));

        String jobTitle;
        while (true) {
            System.out.print("Enter Job Title: ");
            if (scanner.hasNextLine()) {
                jobTitle = scanner.nextLine();
                if (!jobTitle.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Job title cannot be empty.");
                }
            }
        }

        String department;
        while (true) {
            System.out.print("Enter Department: ");
            if (scanner.hasNextLine()) {
                department = scanner.nextLine();
                if (!department.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Department cannot be empty.");
                }
            }
        }

        String hireDateStr;
        while (true) {
            System.out.print("Enter Hire Date (yyyy-MM-dd): ");
            if (scanner.hasNextLine()) {
                hireDateStr = scanner.nextLine();
                try {
                    Date.valueOf(hireDateStr);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            }
        }
        String hireDate = String.valueOf(Date.valueOf(hireDateStr));

        double salary;
        while (true) {
            System.out.print("Enter Salary: ");
            if (scanner.hasNextDouble()) {
                salary = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                if (salary > 0) {
                    break;
                } else {
                    System.out.println("Salary must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid salary.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Enter Phone Number: ");
            if (scanner.hasNextLine()) {
                phoneNumber = scanner.nextLine();
                if (!phoneNumber.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Phone number cannot be empty.");
                }
            }
        }

        String address;
        while (true) {
            System.out.print("Enter Address: ");
            if (scanner.hasNextLine()) {
                address = scanner.nextLine();
                if (!address.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Address cannot be empty.");
                }
            }
        }

        Employee employee = new Employee(employeeID, firstName, lastName, email, dateOfBirth, jobTitle, department, hireDate, salary, phoneNumber, address);
        employeeDAO.createEmployee(employee);
        System.out.println("Employee created successfully.");
    }

    /**
     * Retrieves an employee record from the HR database based on the employee's ID.
     * getEmployeeByID(Scanner scanner): This method prompts the user to enter an employee ID and retrieves the corresponding employee record from the HR database.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void). It prints the employee details to the console.
     */
    private static void getEmployeeByID(Scanner scanner) {
        int employeeID;
        while (true) {
            System.out.print("Enter Employee ID: ");
            if (scanner.hasNextInt()) {
                employeeID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }

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

    /**
     * Updates an existing employee record in the HR database.
     * updateEmployee(Scanner scanner): This method allows the user to update an existing employee record in the HR database.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void).
     */
    private static void updateEmployee(Scanner scanner) {
        int employeeID;
        while (true) {
            System.out.print("Enter Employee ID: ");
            if (scanner.hasNextInt()) {
                employeeID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        Employee employee = employeeDAO.getEmployeeByID(employeeID);
        if (employee != null) {
            String firstName;
            while (true) {
                System.out.print("Enter First Name: ");
                if (scanner.hasNextLine()) {
                    firstName = scanner.nextLine();
                    if (!firstName.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("First name cannot be empty.");
                    }
                }
            }

            String lastName;
            while (true) {
                System.out.print("Enter Last Name: ");
                if (scanner.hasNextLine()) {
                    lastName = scanner.nextLine();
                    if (!lastName.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("Last name cannot be empty.");
                    }
                }
            }

            String email;
            while (true) {
                System.out.print("Enter Email: ");
                if (scanner.hasNextLine()) {
                    email = scanner.nextLine();
                    if (!email.trim().isEmpty() && email.contains("@")) {
                        break;
                    } else {
                        System.out.println("Invalid email. Please enter a valid email address.");
                    }
                }
            }

            String dateOfBirthStr;
            while (true) {
                System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
                if (scanner.hasNextLine()) {
                    dateOfBirthStr = scanner.nextLine();
                    try {
                        Date.valueOf(dateOfBirthStr);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                    }
                }
            }
            Date dateOfBirth = Date.valueOf(dateOfBirthStr);

            String jobTitle;
            while (true) {
                System.out.print("Enter Job Title: ");
                if (scanner.hasNextLine()) {
                    jobTitle = scanner.nextLine();
                    if (!jobTitle.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("Job title cannot be empty.");
                    }
                }
            }

            String department;
            while (true) {
                System.out.print("Enter Department: ");
                if (scanner.hasNextLine()) {
                    department = scanner.nextLine();
                    if (!department.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("Department cannot be empty.");
                    }
                }
            }

            String hireDateStr;
            while (true) {
                System.out.print("Enter Hire Date (yyyy-MM-dd): ");
                if (scanner.hasNextLine()) {
                    hireDateStr = scanner.nextLine();
                    try {
                        Date.valueOf(hireDateStr);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                    }
                }
            }
            Date hireDate = Date.valueOf(hireDateStr);

            double salary;
            while (true) {
                System.out.print("Enter Salary: ");
                if (scanner.hasNextDouble()) {
                    salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    if (salary > 0) {
                        break;
                    } else {
                        System.out.println("Salary must be a positive number.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid salary.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            String phoneNumber;
            while (true) {
                System.out.print("Enter Phone Number: ");
                if (scanner.hasNextLine()) {
                    phoneNumber = scanner.nextLine();
                    if (!phoneNumber.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("Phone number cannot be empty.");
                    }
                }
            }

            String address;
            while (true) {
                System.out.print("Enter Address: ");
                if (scanner.hasNextLine()) {
                    address = scanner.nextLine();
                    if (!address.trim().isEmpty()) {
                        break;
                    } else {
                        System.out.println("Address cannot be empty.");
                    }
                }
            }

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

    /**
     * Deletes an employee record from the HR database based on the employee's ID.
     * deleteEmployee(Scanner scanner): This method allows the user to delete an employee record from the HR database.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void).
     */
    private static void deleteEmployee(Scanner scanner) {
        System.out.println("Delete Employee");
        System.out.println("1. Delete by Employee ID");
        System.out.println("2. Delete by Email");
        int choice;
        while (true) {
            System.out.print("Enter your choice (1 or 2): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        switch (choice) {
            case 1:
                int employeeID;
                while (true) {
                    System.out.print("Enter Employee ID: ");
                    if (scanner.hasNextInt()) {
                        employeeID = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.nextLine(); // Consume invalid input
                    }
                }
                employeeDAO.deleteEmployeeByID(employeeID);
                System.out.println("Employee deleted successfully.");
                break;
            case 2:
                String email;
                while (true) {
                    System.out.print("Enter Employee Email: ");
                    if (scanner.hasNextLine()) {
                        email = scanner.nextLine();
                        if (!email.trim().isEmpty() && email.contains("@")) {
                            break;
                        } else {
                            System.out.println("Invalid email. Please enter a valid email address.");
                        }
                    }
                }
                employeeDAO.deleteEmployeeByEmail(email);
                System.out.println("Employee deleted successfully.");
                break;
        }
    }

    /**
     * Retrieves a list of all employees from the HR database and displays their information.
     * getAllEmployees(): This method retrieves and displays all the employee records from the HR database.
     * This method does not return anything (void). It prints all the employee records to the console.
     */
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

    /**
     * Generates a custom report based on user-specified criteria.
     * generateCustomReport(Scanner scanner): This method allows the user to generate a custom report based on specific criteria.
     * @param scanner the Scanner object used to read user input
     * This method does not return anything (void). It generates and displays the custom report.
     */
    private static void generateCustomReport(Scanner scanner) {
        System.out.println("Generate Custom Report");
        System.out.print("Enter department (or leave blank for all): ");
        String department = scanner.nextLine();

        System.out.print("Enter job title (or leave blank for all): ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter minimum salary (or leave blank for no minimum): ");
        String minSalaryStr = scanner.nextLine();
        double minSalary;
        try {
            minSalary = minSalaryStr.isEmpty() ? 0 : Double.parseDouble(minSalaryStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid minimum salary. Please enter a valid number.");
            return;
        }

        System.out.print("Enter maximum salary (or leave blank for no maximum): ");
        String maxSalaryStr = scanner.nextLine();
        double maxSalary;
        try {
            maxSalary = maxSalaryStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxSalaryStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid maximum salary. Please enter a valid number.");
            return;
        }

        if (minSalary > maxSalary) {
            System.out.println("Minimum salary cannot be greater than maximum salary.");
            return;
        }

        List<Employee> filteredEmployees = employeeDAO.getEmployeesByFilter(department, jobTitle, minSalary, maxSalary);

        System.out.println("Custom Report:");
        System.out.println("Department\tJob Title\tFirst Name\tLast Name\tSalary");
        System.out.println("-----------------------------------------------------------");
        for (Employee employee : filteredEmployees) {
            System.out.printf("%s\t%s\t%s\t%s\t$%.2f%n",
                    employee.getDepartment(), employee.getJobTitle(),
                    employee.getFirstName(), employee.getLastName(),
                    employee.getSalary());
        }
    }
}

