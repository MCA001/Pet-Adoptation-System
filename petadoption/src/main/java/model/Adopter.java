package model;
import javax.persistence.*;

@Entity
@Table(name = "adopter")
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    public Adopter() {
    }

    public Adopter(String name, String contact) {
        this.name = name;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}
