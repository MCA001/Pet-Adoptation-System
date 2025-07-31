//import database.DBConnection;
//import java.sql.Connection;
//
//public class Main {
//    public static void main(String[] args) {
//        Connection conn = DBConnection.getConnection();
//        if (conn != null) {
//            System.out.println("Connected to MySQL!");
//        } else {
//            System.out.println("Connection failed.");
//        }
//    }
//}
//


import dao.adopterDAO;
import dao.adoptionDAO;
import dao.petDAO;
import model.adopter;
import model.adoption;
import model.pet;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        petDAO petDAO = new petDAO();
        adopterDAO adopterDAO = new adopterDAO();
        adoptionDAO adoptionDAO = new adoptionDAO();

        while (true) {
            System.out.println("\n====== Pet Adoption System ======");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Update Pet");
            System.out.println("4. Delete Pet");
            System.out.println("5. Add Adopter");
            System.out.println("6. View All Adopters");
            System.out.println("7. Add Adoption Record");
            System.out.println("8. View All Adoptions");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter pet name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter pet type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter pet age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    String status = "Available";

                    pet pet = new pet(0, name, type, age, status);
                    petDAO.addPet(pet);
                }

                case 2 -> {
                    List<pet> pets = petDAO.getAllpets();
                    System.out.println("\nAll Pets:");
                    for (pet p : pets) {
                        System.out.println(p.getId() + " - " + p.getName() + " (" + p.getType() + ", " + p.getAge() + " yrs) - " + p.getStatus());
                    }
                }

                case 3 -> {
                    System.out.print("Enter Pet ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter new age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new status: ");
                    String status = sc.nextLine();

                    pet pet = new pet(id, name, type, age, status);
                    petDAO.updatePet(pet);
                }

                case 4 -> {
                    System.out.print("Enter Pet ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    petDAO.deletePet(id);
                }

                case 5 -> {
                    System.out.print("Enter adopter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter adopter contact info: ");
                    String contact = sc.nextLine();

                    adopter adopter = new adopter(0, name, contact);
                    adopterDAO.addAdopter(adopter);
                }

                case 6 -> {
                    List<adopter> adopters = adopterDAO.getAllAdopters();
                    System.out.println("\nAll Adopters:");
                    for (adopter a : adopters) {
                        System.out.println(a.getId() + " - " + a.getName() + " | Contact: " + a.getContact());
                    }
                }

                case 7 -> {
                    System.out.print("Enter Pet ID to adopt: ");
                    int petId = sc.nextInt();
                    System.out.print("Enter Adopter ID: ");
                    int adopterId = sc.nextInt();
                    sc.nextLine();

                    adoption adoption = new adoption(0, petId, adopterId, LocalDate.now());
                    adoptionDAO.addAdoption(adoption);
                }

                case 8 -> {
                    List<adoption> adoptions = adoptionDAO.getAllAdoptions();
                    System.out.println("\nAll Adoptions:");
                    for (adoption a : adoptions) {
                        System.out.println("Adoption ID: " + a.getId() +
                                ", Pet ID: " + a.getPetId() +
                                ", Adopter ID: " + a.getAdopterId() +
                                ", Date: " + a.getAdoptionDate());
                    }
                }

                case 0 -> {
                    System.out.println("Thank you for using the Pet Adoption System.");
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

