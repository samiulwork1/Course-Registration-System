package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entity.*;
import fileio.FileManager;

public class CourseRegistrationFrame extends JFrame implements ActionListener {
    private JLabel titleLabel, studentNameLabel, studentIdLabel, departmentLabel;
    private JLabel courseCodeLabel, courseTitleLabel, creditLabel, indexLabel;
    private JTextField studentNameField, studentIdField, departmentField;
    private JTextField courseCodeField, courseTitleField, creditField, indexField;
    private JButton insertButton, updateButton, getButton, deleteButton, clearButton, saveButton, loadButton;
    private JTextArea displayArea;

    private Registration[] registrations = new Registration[50];
    private FileManager fileManager = new FileManager("registrations.txt");

    public CourseRegistrationFrame() {
        super("Course Registration System");
        setSize(850, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 15);

        titleLabel = new JLabel("Course Registration System");
        titleLabel.setBounds(260, 10, 350, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        int x = 30, y = 70, w = 140, h = 30, gap = 40;

        indexLabel = createLabel("Index No:", x, y, w, h, labelFont);
        indexField = createTextField(x + w, y, 170, h, fieldFont);

        studentNameLabel = createLabel("Student Name:", x, y += gap, w, h, labelFont);
        studentNameField = createTextField(x + w, y, 170, h, fieldFont);

        studentIdLabel = createLabel("Student ID:", x, y += gap, w, h, labelFont);
        studentIdField = createTextField(x + w, y, 170, h, fieldFont);

        departmentLabel = createLabel("Department:", x, y += gap, w, h, labelFont);
        departmentField = createTextField(x + w, y, 170, h, fieldFont);

        courseCodeLabel = createLabel("Course Code:", x, y += gap, w, h, labelFont);
        courseCodeField = createTextField(x + w, y, 170, h, fieldFont);

        courseTitleLabel = createLabel("Course Title:", x, y += gap, w, h, labelFont);
        courseTitleField = createTextField(x + w, y, 170, h, fieldFont);

        creditLabel = createLabel("Credit:", x, y += gap, w, h, labelFont);
        creditField = createTextField(x + w, y, 170, h, fieldFont);

        insertButton = createButton("Insert", 30, 370, 120, 35);
        updateButton = createButton("Update", 160, 370, 120, 35);
        getButton = createButton("Get", 30, 415, 120, 35);
        deleteButton = createButton("Delete", 160, 415, 120, 35);
        clearButton = createButton("Clear", 95, 460, 120, 35);
        saveButton = createButton("Save File", 30, 505, 120, 35);
        loadButton = createButton("Load File", 160, 505, 120, 35);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(350, 70, 460, 470);
        add(scrollPane);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        add(label);
        return label;
    }

    private JTextField createTextField(int x, int y, int w, int h, Font font) {
        JTextField field = new JTextField();
        field.setBounds(x, y, w, h);
        field.setFont(font);
        add(field);
        return field;
    }

    private JButton createButton(String text, int x, int y, int w, int h) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.addActionListener(this);
        add(button);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == insertButton) {
                insertRegistration();
            } else if (e.getSource() == updateButton) {
                updateRegistration();
            } else if (e.getSource() == getButton) {
                getRegistration();
            } else if (e.getSource() == deleteButton) {
                deleteRegistration();
            } else if (e.getSource() == clearButton) {
                clearFields();
            } else if (e.getSource() == saveButton) {
                saveToFile();
            } else if (e.getSource() == loadButton) {
                loadFromFile();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Index and Credit must be numbers.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void insertRegistration() {
        int index = Integer.parseInt(indexField.getText());
        checkIndex(index);

        if (registrations[index] != null) {
            JOptionPane.showMessageDialog(this, "This index already has a registration.");
            return;
        }

        registrations[index] = createRegistrationFromFields();
        updateDisplay();

        JOptionPane.showMessageDialog(this, "Registration inserted successfully.");
    }

    private void updateRegistration() {
        int index = Integer.parseInt(indexField.getText());
        checkIndex(index);

        if (registrations[index] == null) {
            JOptionPane.showMessageDialog(this, "No registration found at this index.");
            return;
        }
        registrations[index] = createRegistrationFromFields();
        updateDisplay();

        JOptionPane.showMessageDialog(this, "Registration updated successfully.");
    }

    private void getRegistration() {
        int index = Integer.parseInt(indexField.getText());
        checkIndex(index);

        if (registrations[index] == null) {
            JOptionPane.showMessageDialog(this, "No registration found at this index.");
            return;
        }

        Registration r = registrations[index];

        studentNameField.setText(r.getStudent().getName());
        studentIdField.setText(r.getStudent().getId());
        departmentField.setText(r.getStudent().getDepartment());
        courseCodeField.setText(r.getCourse().getCourseCode());
        courseTitleField.setText(r.getCourse().getCourseTitle());
        creditField.setText(String.valueOf(r.getCourse().getCredit()));
    }

    private void deleteRegistration() {
        int index = Integer.parseInt(indexField.getText());
        checkIndex(index);

        if (registrations[index] == null) {
            JOptionPane.showMessageDialog(this, "No registration found at this index.");
            return;
        }

        registrations[index] = null;
        updateDisplay();

        JOptionPane.showMessageDialog(this, "Registration deleted successfully.");
    }

    private Registration createRegistrationFromFields() {
        String studentName = studentNameField.getText();
        String studentId = studentIdField.getText();
        String department = departmentField.getText();
        String courseCode = courseCodeField.getText();
        String courseTitle = courseTitleField.getText();
        int credit = Integer.parseInt(creditField.getText());

        if (studentName.isEmpty() || studentId.isEmpty() || department.isEmpty() ||
                courseCode.isEmpty() || courseTitle.isEmpty()) {
            throw new IllegalArgumentException("Please fill all fields.");
        }

        Student student = new Student(studentName, studentId, department);
        Course course = new Course(courseCode, courseTitle, credit);

        return new Registration(student, course);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= registrations.length) {
            throw new ArrayIndexOutOfBoundsException("Index must be from 0 to 49.");
        }
    }

    private void updateDisplay() {
        String data = "";

        for (int i = 0; i < registrations.length; i++) {
            if (registrations[i] != null) {
                data += "Index: " + i + "\n";
                data += registrations[i].getRegistrationDetails();
                data += "------------------------------\n";
            }
        }

        displayArea.setText(data);
    }

    private void clearFields() {
        indexField.setText("");
        studentNameField.setText("");
        studentIdField.setText("");
        departmentField.setText("");
        courseCodeField.setText("");
        courseTitleField.setText("");
        creditField.setText("");
    }

    private void saveToFile() {
        String data = displayArea.getText();
        fileManager.writeData(data);

        JOptionPane.showMessageDialog(this, "Data saved to registrations.txt");
    }

    private void loadFromFile() {
        String data = fileManager.readData();
        displayArea.setText(data);

        JOptionPane.showMessageDialog(this, "Data loaded from registrations.txt");
    }
}