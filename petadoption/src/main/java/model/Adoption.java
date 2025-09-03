package model;
import javax.persistence.*;
import java.security.cert.Extension;
import java.time.LocalDate;

@Entity
@Table(name = "adoption")
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pet_id")
    private int petId;

    @Column(name = "adopter_id")
    private int adopterId;

    @Column(name = "adoption_date")
    private LocalDate adoptionDate;  // storing as String for now

    public Adoption() {
    }

    public Adoption(int adopterId, int petId, String adoptionDate) {
        Extension adopter = null;
        this.adopterId = Integer.parseInt(adopter.getId());
        Extension pet = null;
        this.petId = Integer.parseInt(pet.getId());
        this.adoptionDate = LocalDate.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getAdopterId() {
        return adopterId;
    }
    public void setAdopterId(int adopterId) {
        this.adopterId = adopterId;
    }

    public LocalDate getAdoptionDate() { return adoptionDate; }
    public void setAdoptionDate(LocalDate adoptionDate) { this.adoptionDate = adoptionDate; }


    @Override
    public String toString() {
        return "Adoption [id=" + id + ", adopterId=" + adopterId +
                ", petId=" + petId + ", adoptionDate=" + adoptionDate + "]";
    }
}
