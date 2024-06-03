Employee Management System

The Employee Management System is a Java-based application that allows users to manage employee data, including creating, retrieving, updating, and deleting employee records. The system also provides the ability to import employee data from a file and generate custom reports.

Classes
The project consists of two main classes:
Employee
EmployeeDAO

Employee Class
The Employee class represents an employee and contains the following properties:

employeeID: The unique identifier for the employee.
firstName: The first name of the employee.
lastName: The last name of the employee.
email: The email address of the employee.
dateOfBirth: The date of birth of the employee.
jobTitle: The job title of the employee.
department: The department the employee belongs to.
hireDate: The date the employee was hired.
salary: The salary of the employee.
phoneNumber: The phone number of the employee.
address: The address of the employee.
The Employee class provides methods to get and set the values of these properties.

EmployeeDAO Class
The EmployeeDAO (Data Access Object) class is responsible for interacting with the HR database to perform CRUD (Create, Read, Update, Delete) operations on employee data. The class provides the following methods:

createEmployee(Employee employee): Creates a new employee record in the database.
getEmployeeByID(int id): Retrieves an employee record from the database by their ID.
updateEmployee(Employee employee): Updates an existing employee record in the database.
deleteEmployee(int id): Deletes an employee record from the database by their ID.
getAllEmployees(): Retrieves all employee records from the database.
importEmployeesFromFile(String filePath): Imports employee data from a file and adds them to the database.
generateCustomReport(String criteria): Generates a custom report based on the specified criteria.

Main Class
The Main class is the entry point of the Employee Management System. It provides the user interface and handles the user's interactions with the system. The Main class creates an instance of the EmployeeDAO class and calls the appropriate methods based on the user's selection from the menu.

The Main class includes the following methods:
main(String[] args): The main entry point of the application.
displayMenu(): Displays the main menu of the Employee Management System.
createEmployee(Scanner scanner): Prompts the user to enter employee details and creates a new employee record.
getEmployeeByID(Scanner scanner): Prompts the user to enter an employee ID and retrieves the corresponding employee record.
updateEmployee(Scanner scanner): Prompts the user to enter updated employee details and updates the corresponding employee record.
deleteEmployee(Scanner scanner): Prompts the user to enter an employee ID and deletes the corresponding employee record.
getAllEmployees(): Retrieves and displays all employee records.
importEmployeesFromFile(Scanner scanner): Prompts the user to enter a file path and imports employee data from the file.
generateCustomReport(Scanner scanner): Prompts the user to enter custom report criteria and generates the report.


Usage
The main(String[] args) method is the entry point of the application. It creates a Scanner object to handle user input and enters a do-while loop to display the main menu and handle the user's selections.
The other methods in the Main class, such as createEmployee(), getEmployeeByID(), updateEmployee(), and so on, handle the user's interactions with the system by calling the corresponding methods in the EmployeeDAO class.
To use the Employee Management System, run the Main class. The application will display the main menu, and the user can select the desired option by entering the corresponding number.

Dependencies
The Employee Management System does not have any external dependencies. It uses the built-in Java libraries for file I/O, date handling, and user input.

Future Improvements
Implement a more robust database connection and management system.
Add support for more advanced reporting.
