package entity;

// Abstraction: abstract class
public abstract class Person {
    private String name; // Encapsulation
    private String id;

    public Person() {
        this.name = "Unknown";
        this.id = "N/A";
    }

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String getDetails();
}
