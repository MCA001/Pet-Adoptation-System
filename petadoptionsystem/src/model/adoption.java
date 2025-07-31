package model;

import java.time.LocalDate;


public class adoption {
    private int id;
    private int petId;
    private int adopterId;
    private LocalDate adoptionDate;

    //constructor
    public adoption() {}

    // Parameterized constructor
    public adoption(int id, int petId, int adopterId, LocalDate adoptionDate) {
        this.id = id;
        this.petId = petId;
        this.adopterId = adopterId;
        this.adoptionDate = adoptionDate;
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

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
}

