package entity;

// Inheritance: Student inherits Person
public class Student extends Person {
    private String department;

    public Student() {
        super();
        this.department = "N/A";
    }

    public Student(String name, String id, String department) {
        super(name, id);
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    // Polymorphism: overriding abstract method
    @Override
    public String getDetails() {
        return "Student Name: " + getName() + "\n" +
               "Student ID: " + getId() + "\n" +
               "Department: " + department + "\n";
    }
}
