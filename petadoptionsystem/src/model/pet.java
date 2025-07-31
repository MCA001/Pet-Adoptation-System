package model;

public class pet {
    private int id;
    private String name;
    private String type;
    private int age;
    private String status;

    // Constructors
    public pet() {}
    public pet(int id, String name, String type, int age, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
