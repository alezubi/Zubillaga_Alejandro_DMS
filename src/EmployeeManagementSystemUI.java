import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Author: Alejandro Zubillaga
 * Course: 202430-CEN-3024C-31950
 * Date: Jun 26, 2024
 * Software Development I
 *
 * A graphical user interface for an Employee Management System.
 */
public class EmployeeManagementSystemUI {
    private JFrame frame; // The main frame for the application
    // private JTextField choiceField;
    // unused field that could be used in the future

    public EmployeeManagementSystemUI() {
        createUI();
    }
//
    private void createUI() {

        frame = new JFrame("Employee Management System");// Create the main frame with title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        frame.setLayout(new BorderLayout()); // Use BorderLayout for main frame layout

        // Create a title panel with a header
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // added 10px vertical spacing
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // added 10px padding around title
        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.decode("#333")); // dark gray color for title
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Create a menu panel with a grid layout
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some whitespace around components
        gbc.anchor = GridBagConstraints.WEST; // align components to left
        gbc.fill = GridBagConstraints.HORIZONTAL; // allow buttons to expand horizontally

        // Common settings for all buttons
        gbc.weightx = 1; // makes the button take up all available horizontal space

        // Create buttons and add to menu panel
        addButtonToPanel(menuPanel, gbc, "Create Employee", e -> Main.createEmployee(), 0);
        addButtonToPanel(menuPanel, gbc, "Get Employee by ID", e -> Main.getEmployeeByID(), 1);
        addButtonToPanel(menuPanel, gbc, "Update Employee", e -> Main.updateEmployee(), 2);
        addButtonToPanel(menuPanel, gbc, "Delete Employee", e -> Main.deleteEmployee(), 3);
        addButtonToPanel(menuPanel, gbc, "Get All Employees", e -> Main.getAllEmployees(), 4);
        addButtonToPanel(menuPanel, gbc, "Import Employees from File", e -> Main.importEmployeesFromFile(), 5);
        addButtonToPanel(menuPanel, gbc, "Generate Custom Report", e -> Main.generateCustomReport(), 6);
        addButtonToPanel(menuPanel, gbc, "Exit", e -> System.exit(0), 7);

        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // added 10px padding around menu
        frame.add(menuPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Helper method to add a button to a specified panel with given constraints.
     * @param panel     The panel to add the button to.
     * @param gbc       GridBagConstraints for button placement.
     * @param buttonText The text to display on the button.
     * @param listener  ActionListener for button click events.
     * @param gridy     The grid row index for placing the button.
     */
    private void addButtonToPanel(JPanel panel, GridBagConstraints gbc, String buttonText, ActionListener listener, int gridy) {
        JButton button = new JButton(buttonText);
        button.addActionListener(listener);
        gbc.gridy = gridy;
        panel.add(button, gbc);
    }
    /**
     * Main method to launch the application on the Event Dispatch Thread (EDT).
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread (may be needed in the future)
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementSystemUI();
            }
        });
    }
}