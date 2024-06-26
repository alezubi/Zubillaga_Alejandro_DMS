import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.InputMismatchException;
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
        int choice = 0;


            do {
                displayMenu();
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    switch (choice) {
                        case 1:
                            createEmployee();
                            break;
                        case 2:
                            getEmployeeByID();
                            break;
                        case 3:
                            updateEmployee();
                            break;
                        case 4:
                            deleteEmployee();
                            break;
                        case 5:
                            getAllEmployees();
                            break;
                        case 6:
                            importEmployeesFromFile();
                            break;
                        case 7:
                            generateCustomReport();
                            break;
                        case 8:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine(); // Consume the invalid input
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
     * importEmployeesFromFile(Scanner): This method allows the user to import employee data from a file and add the employees to the HR database.
     * the Scanner object used to read user input
     * This method does not return anything (void).
     */
    public static void importEmployeesFromFile() {
        String filePath = JOptionPane.showInputDialog("Enter the file path:");
        if (filePath != null) {
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
                        JOptionPane.showMessageDialog(null, "Employee added successfully: " + employee.getFirstName() + " " + employee.getLastName());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid employee data format in line: " + line, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid employee data format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Creates a new employee record in the HR database.
     * createEmployee(Scanner scanner): This method prompts the user to enter employee details and creates a new employee record in the HR database.
     * the Scanner object used to read user input
     * This method does not return anything (void).
     */
    public static void createEmployee() {
        int employeeID;
        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter Employee ID:");
            if (idInput != null) {
                try {
                    employeeID = Integer.parseInt(idInput);
                    if (employeeID <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                    continue;
                }
            } else {
                return;
            }
        }

        String firstName;
        while (true) {
            firstName = JOptionPane.showInputDialog("Enter First Name:");
            if (firstName != null) {
                if (!firstName.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "First name cannot be empty.");
                }
            } else {
                return;
            }
        }

        String lastName;
        while (true) {
            lastName = JOptionPane.showInputDialog("Enter Last Name:");
            if (lastName != null) {
                if (!lastName.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Last name cannot be empty.");
                }
            } else {
                return;
            }
        }

        String email;
        while (true) {
            email = JOptionPane.showInputDialog("Enter Email:");
            if (email != null) {
                if (!email.trim().isEmpty() && email.contains("@")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email. Please enter a valid email address.");
                }
            } else {
                return;
            }
        }

        String dateOfBirthStr;
        while (true) {
            dateOfBirthStr = JOptionPane.showInputDialog("Enter Date of Birth (yyyy-MM-dd):");
            if (dateOfBirthStr != null) {
                try {
                    Date.valueOf(dateOfBirthStr);
                    break;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            } else {
                return;
            }
        }
        String dateOfBirth = String.valueOf(Date.valueOf(dateOfBirthStr));

        String jobTitle;
        while (true) {
            jobTitle = JOptionPane.showInputDialog("Enter Job Title:");
            if (jobTitle != null) {
                if (!jobTitle.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Job title cannot be empty.");
                }
            } else {
                return;
            }
        }

        String department;
        while (true) {
            department = JOptionPane.showInputDialog("Enter Department:");
            if (department != null) {
                if (!department.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Department cannot be empty.");
                }
            } else {
                return;
            }
        }

        String hireDateStr;
        while (true) {
            hireDateStr = JOptionPane.showInputDialog("Enter Hire Date (yyyy-MM-dd):");
            if (hireDateStr != null) {
                try {
                    Date.valueOf(hireDateStr);
                    break;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            } else {
                return;
            }
        }
        String hireDate = String.valueOf(Date.valueOf(hireDateStr));

        double salary;
        while (true) {
            String salaryInput = JOptionPane.showInputDialog("Enter Salary:");
            if (salaryInput != null) {
                try {
                    salary = Double.parseDouble(salaryInput);
                    if (salary <= 0) {
                        JOptionPane.showMessageDialog(null, "Salary must be a positive number.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid salary.");
                    continue;
                }
            } else {
                return;
            }
        }

        String phoneNumber;
        while (true) {
            phoneNumber = JOptionPane.showInputDialog("Enter Phone Number:");
            if (phoneNumber != null) {
                if (!phoneNumber.trim().isEmpty() && phoneNumber.length() == 10) {
                    try {
                        Long.parseLong(phoneNumber);
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid 10-digit phone number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid 10-digit phone number.");
                }
            } else {
                return;
            }
        }

        String address;
        while (true) {
            address = JOptionPane.showInputDialog("Enter Address:");
            if (address != null) {
                if (!address.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Address cannot be empty.");
                }
            } else {
                return;
            }
        }

        Employee employee = new Employee(employeeID, firstName, lastName, email, dateOfBirth, jobTitle, department, hireDate, salary, phoneNumber, address);
        employeeDAO.createEmployee(employee);
        JOptionPane.showMessageDialog(null, "Employee created successfully.");
    }

    /**
     * Retrieves an employee record from the HR database based on the employee's ID.
     * getEmployeeByID(Scanner scanner): This method prompts the user to enter an employee ID and retrieves the corresponding employee record from the HR database.
     * the Scanner object used to read user input
     * This method does not return anything (void). It prints the employee details to the console.
     */

   public static void getEmployeeByID() {
        String idInput = JOptionPane.showInputDialog("Enter Employee ID:");
        if (idInput != null) {
            int employeeID;
            try {
                employeeID = Integer.parseInt(idInput);
                if (employeeID <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                return;
            }

            Employee employee = employeeDAO.getEmployeeByID(employeeID);
            if (employee != null) {
                String employeeInfo = "Employee Details:\n"
                        + "ID: " + employee.getEmployeeID() + "\n"
                        + "First Name: " + employee.getFirstName() + "\n"
                        + "Last Name: " + employee.getLastName() + "\n"
                        + "Email: " + employee.getEmail() + "\n"
                        + "Date of Birth: " + employee.getDateOfBirth() + "\n"
                        + "Job Title: " + employee.getJobTitle() + "\n"
                        + "Department: " + employee.getDepartment() + "\n"
                        + "Hire Date: " + employee.getHireDate() + "\n"
                        + "Salary: " + employee.getSalary() + "\n"
                        + "Phone Number: " + employee.getPhoneNumber() + "\n"
                        + "Address: " + employee.getAddress();
                JOptionPane.showMessageDialog(null, employeeInfo);
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            }
        }
    }


    /**
     * Updates an existing employee record in the HR database.
     * updateEmployee(Scanner scanner): This method allows the user to update an existing employee record in the HR database.
     * the Scanner object used to read user input
     * This method does not return anything (void).
     */
    public static void updateEmployee() {
        int employeeID;
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter Employee ID:", "Update Employee", JOptionPane.QUESTION_MESSAGE);
            if (input == null) { // user clicked "Cancel"
                return; // exit the method
            }
            try {
                employeeID = Integer.parseInt(input);
                if (employeeID >= 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID cannot be negative. Please enter a valid integer.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            }
        }

        Employee employee = employeeDAO.getEmployeeByID(employeeID);
        if (employee != null) {
            String firstName;
            while (true) {
                firstName = JOptionPane.showInputDialog("Enter First Name:");
                if (!firstName.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "First name cannot be empty.");
                }
            }

            String lastName;
            while (true) {
                lastName = JOptionPane.showInputDialog("Enter Last Name:");
                if (!lastName.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Last name cannot be empty.");
                }
            }

            String email;
            while (true) {
                email = JOptionPane.showInputDialog("Enter Email:");
                if (!email.trim().isEmpty() && email.contains("@")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email. Please enter a valid email address.");
                }
            }

            String dateOfBirthStr;
            while (true) {
                dateOfBirthStr = JOptionPane.showInputDialog("Enter Date of Birth (yyyy-MM-dd):");
                try {
                    Date.valueOf(dateOfBirthStr);
                    break;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            }
            Date dateOfBirth = Date.valueOf(dateOfBirthStr);

            String jobTitle;
            while (true) {
                jobTitle = JOptionPane.showInputDialog("Enter Job Title:");
                if (!jobTitle.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Job title cannot be empty.");
                }
            }

            String department;
            while (true) {
                department = JOptionPane.showInputDialog("Enter Department:");
                if (!department.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Department cannot be empty.");
                }
            }

            String hireDateStr;
            while (true) {
                hireDateStr = JOptionPane.showInputDialog("Enter Hire Date (yyyy-MM-dd):");
                try {
                    Date.valueOf(hireDateStr);
                    break;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
                }
            }
            Date hireDate = Date.valueOf(hireDateStr);

            double salary;
            while (true) {
                String salaryStr = JOptionPane.showInputDialog("Enter Salary:");
                try {
                    salary = Double.parseDouble(salaryStr);
                    if (salary > 0) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Salary must be a positive number.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid salary.");
                }
            }

            int phoneNumber;
            while (true) {
                String phoneNumberStr = JOptionPane.showInputDialog("Enter Phone Number:");
                try {
                    phoneNumber = Integer.parseInt(phoneNumberStr);
                    if (String.valueOf(phoneNumber).length() == 10) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a 10-digit phone number.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid phone number.");
                }
            }

            String address;
            while (true) {
                address = JOptionPane.showInputDialog("Enter Address:");
                if (!address.trim().isEmpty()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Address cannot be empty.");
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
            employee.setPhoneNumber(String.valueOf(phoneNumber));
            employee.setAddress(address);

            employeeDAO.updateEmployee(employee);
            JOptionPane.showMessageDialog(null, "Employee updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Employee not found.");
        }
    }
    /**
     * Deletes an employee record from the HR database based on the employee's ID.
     * deleteEmployee(Scanner scanner): This method allows the user to delete an employee record from the HR database.
     *the Scanner object used to read user input
     * This method does not return anything (void).
     */
    public static void deleteEmployee() {
        String[] options = {"Delete by Employee ID", "Delete by Email"};
        String choice = (String) JOptionPane.showInputDialog(null, "Delete Employee", "Delete Employee", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice.equals("Delete by Employee ID")) {
            int employeeID;
            while (true) {
                String input = JOptionPane.showInputDialog("Enter Employee ID:");
                try {
                    employeeID = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                }
            }
            employeeDAO.deleteEmployeeByID(employeeID);
        } else if (choice.equals("Delete by Email")) {
            String email;
            while (true) {
                email = JOptionPane.showInputDialog("Enter Employee Email:");
                if (!email.trim().isEmpty() && email.contains("@")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email. Please enter a valid email address.");
                }
            }
            employeeDAO.deleteEmployeeByEmail(email);
        }
        JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
    }

    /**
     * Retrieves a list of all employees from the HR database and displays their information.
     * getAllEmployees(): This method retrieves and displays all the employee records from the HR database.
     * This method does not return anything (void). It prints all the employee records to the console.
     */
    public static void getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No employees found.");
        } else {
            StringBuilder employeeList = new StringBuilder("All Employees:\n");
            for (Employee employee : employees) {
                employeeList.append("ID: ").append(employee.getEmployeeID()).append("\n");
                employeeList.append("First Name: ").append(employee.getFirstName()).append("\n");
                employeeList.append("Last Name: ").append(employee.getLastName()).append("\n");
                employeeList.append("Email: ").append(employee.getEmail()).append("\n");
                employeeList.append("Date of Birth: ").append(employee.getDateOfBirth()).append("\n");
                employeeList.append("Job Title: ").append(employee.getJobTitle()).append("\n");
                employeeList.append("Department: ").append(employee.getDepartment()).append("\n");
                employeeList.append("Hire Date: ").append(employee.getHireDate()).append("\n");
                employeeList.append("Salary: ").append(employee.getSalary()).append("\n");
                employeeList.append("Phone Number: ").append(employee.getPhoneNumber()).append("\n");
                employeeList.append("Address: ").append(employee.getAddress()).append("\n\n");
            }

            JTextArea textArea = new JTextArea(employeeList.toString(), 20, 40);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "All Employees", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Generates a custom report based on user-specified criteria.
     * generateCustomReport(Scanner scanner): This method allows the user to generate a custom report based on specific criteria.
     * the Scanner object used to read user input
     * This method does not return anything (void). It generates and displays the custom report.
     */
    public static void generateCustomReport() {
        String department = JOptionPane.showInputDialog("Enter department (or leave blank for all): ");
        if (department == null) { // user clicked "Cancel"
            return; // go back to main menu
        }

        String jobTitle = JOptionPane.showInputDialog("Enter job title (or leave blank for all): ");
        if (jobTitle == null) {
            return;
        }

        String minSalaryStr = JOptionPane.showInputDialog("Enter minimum salary (or leave blank for no minimum): ");
        if (minSalaryStr == null) {
            return;
        }

        double minSalary;
        try {
            minSalary = minSalaryStr.isEmpty() ? 0 : Double.parseDouble(minSalaryStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid minimum salary. Please enter a valid number.");
            return;
        }

        String maxSalaryStr = JOptionPane.showInputDialog("Enter maximum salary (or leave blank for no maximum): ");
        if (maxSalaryStr == null) {
            return;
        }

        double maxSalary;
        try {
            maxSalary = maxSalaryStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxSalaryStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid maximum salary. Please enter a valid number.");
            return;
        }

        if (minSalary > maxSalary) {
            JOptionPane.showMessageDialog(null, "Minimum salary cannot be greater than maximum salary.");
            return;
        }

        List<Employee> filteredEmployees = employeeDAO.getEmployeesByFilter(department, jobTitle, minSalary, maxSalary);

        // Creates a panel to hold the report content
        JPanel reportPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); // Monospaced font for consistent spacing
        JScrollPane scrollPane = new JScrollPane(textArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        // Prepares the report content
        StringBuilder report = new StringBuilder();
        report.append(String.format("%-20s %-20s %-15s %-15s %s\n", "Department", "Job Title", "First Name", "Last Name", "Salary"));
        report.append("------------------------------------------------------------------------------\n");
        for (Employee employee : filteredEmployees) {
            report.append(String.format("%-20s %-20s %-15s %-15s $%.2f\n",
                    employee.getDepartment(), employee.getJobTitle(),
                    employee.getFirstName(), employee.getLastName(),
                    employee.getSalary()));
        }
        textArea.setText(report.toString());

        // Create a dialog to display the report
        JDialog dialog = new JDialog();
        dialog.setTitle("Custom Report");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(reportPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);
    }
}

