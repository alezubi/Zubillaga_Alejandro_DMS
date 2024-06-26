import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementSystemUI {
    private JFrame frame;
    private JTextField choiceField;

    public EmployeeManagementSystemUI() {
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Database Management System - Zubillaga_Alejandro_DMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JButton createEmployeeButton = new JButton("Create Employee");
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.createEmployee();
            }
        });
        menuPanel.add(createEmployeeButton);

        JButton getEmployeeByIDButton = new JButton("Get Employee by ID");
        getEmployeeByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getEmployeeByID();
            }
        });
        menuPanel.add(getEmployeeByIDButton);

        JButton updateEmployeeButton = new JButton("Update Employee");
        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.updateEmployee();
            }
        });
        menuPanel.add(updateEmployeeButton);

        JButton deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.deleteEmployee();
            }
        });
        menuPanel.add(deleteEmployeeButton);

        JButton getAllEmployeesButton = new JButton("Get All Employees");
        getAllEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getAllEmployees();
            }
        });
        menuPanel.add(getAllEmployeesButton);

        JButton importEmployeesFromFileButton = new JButton("Import Employees from File");
        importEmployeesFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.importEmployeesFromFile();
            }
        });
        menuPanel.add(importEmployeesFromFileButton);

        JButton generateCustomReportButton = new JButton("Generate Custom Report");
        generateCustomReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.generateCustomReport();
            }
        });
        menuPanel.add(generateCustomReportButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementSystemUI();
            }
        });
    }
}