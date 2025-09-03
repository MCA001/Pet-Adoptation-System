package app;

import model.Adopter;
import model.Pet;
import model.Adoption;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Pet Adoption System =====");
            System.out.println("1. Adopter Management");
            System.out.println("2. Pet Management");
            System.out.println("3. Adoption Management");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> adopterMenu(sc);
                case 2 -> petMenu(sc);
                case 3 -> adoptionMenu(sc);
                case 4 -> {
                    factory.close();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    // ---------------- ADOPTER MENU ----------------
    private static void adopterMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Adopter Management ---");
            System.out.println("1. Insert Adopter");
            System.out.println("2. View All Adopters");
            System.out.println("3. Update Adopter");
            System.out.println("4. Delete Adopter");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> insertAdopter(sc);
                case 2 -> viewAdopters();
                case 3 -> updateAdopter(sc);
                case 4 -> deleteAdopter(sc);
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void insertAdopter(Scanner sc) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        String contact;
        while (true) {
            System.out.print("Enter 10-digit contact: ");
            contact = sc.nextLine();
            if (contact.matches("\\d{10}")) break;
            System.out.println("Invalid contact! Try again.");
        }

        Adopter adopter = new Adopter(name, contact);
        session.save(adopter);

        tx.commit();
        session.close();
        System.out.println("Adopter saved successfully!");
    }

    private static void viewAdopters() {
        Session session = factory.openSession();
        List<Adopter> adopters = session.createQuery("from Adopter", Adopter.class).list();

        if (adopters.isEmpty()) {
            System.out.println("No adopters found!");
        } else {
            adopters.forEach(a ->
                    System.out.println("ID: " + a.getId() + ", Name: " + a.getName() + ", Contact: " + a.getContact()));
        }
        session.close();
    }

    private static void updateAdopter(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter adopter ID: ");
        int id = sc.nextInt(); sc.nextLine();

        Adopter adopter = session.get(Adopter.class, id);
        if (adopter != null) {
            System.out.print("Enter new name: ");
            adopter.setName(sc.nextLine());

            System.out.print("Enter new contact: ");
            adopter.setContact(sc.nextLine());

            session.update(adopter);
            tx.commit();
            System.out.println("Adopter updated successfully!");
        } else {
            System.out.println("Adopter not found.");
            tx.rollback();
        }
        session.close();
    }

    private static void deleteAdopter(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter adopter ID: ");
        int id = sc.nextInt();

        Adopter adopter = session.get(Adopter.class, id);
        if (adopter != null) {
            session.delete(adopter);
            tx.commit();
            System.out.println("Adopter deleted successfully!");
        } else {
            System.out.println("Adopter not found.");
            tx.rollback();
        }
        session.close();
    }


    // ---------------- PET MENU ----------------
    private static void petMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Pet Management ---");
            System.out.println("1. Insert Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Update Pet");
            System.out.println("4. Delete Pet");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> insertPet(sc);
                case 2 -> viewPets();
                case 3 -> updatePet(sc);
                case 4 -> deletePet(sc);
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void insertPet(Scanner sc) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter pet name: ");
        String name = sc.nextLine();

        System.out.print("Enter pet type: ");
        String type = sc.nextLine();

        System.out.print("Enter pet age: ");
        int age = sc.nextInt(); sc.nextLine();

        Pet pet = new Pet(name, type, age, "Available");
        session.save(pet);

        tx.commit();
        session.close();
        System.out.println("Pet saved successfully!");
    }

    private static void viewPets() {
        Session session = factory.openSession();
        List<Pet> pets = session.createQuery("from Pet", Pet.class).list();

        if (pets.isEmpty()) {
            System.out.println("No pets found!");
        } else {
            pets.forEach(p ->
                    System.out.println("ID: " + p.getId() + ", Name: " + p.getName() +
                            ", Type: " + p.getType() + ", Age: " + p.getAge() +
                            ", Status: " + p.getStatus()));
        }
        session.close();
    }

    private static void updatePet(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter pet ID: ");
        int id = sc.nextInt(); sc.nextLine();

        Pet pet = session.get(Pet.class, id);
        if (pet != null) {
            System.out.print("Enter new name: ");
            pet.setName(sc.nextLine());

            System.out.print("Enter new type: ");
            pet.setType(sc.nextLine());

            System.out.print("Enter new age: ");
            pet.setAge(sc.nextInt()); sc.nextLine();

            System.out.print("Enter new status: ");
            pet.setStatus(sc.nextLine());

            session.update(pet);
            tx.commit();
            System.out.println("Pet updated successfully!");
        } else {
            System.out.println("Pet not found.");
            tx.rollback();
        }
        session.close();
    }

    private static void deletePet(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter pet ID: ");
        int id = sc.nextInt();

        Pet pet = session.get(Pet.class, id);
        if (pet != null) {
            session.delete(pet);
            tx.commit();
            System.out.println("Pet deleted successfully!");
        } else {
            System.out.println("Pet not found.");
            tx.rollback();
        }
        session.close();
    }


    // ---------------- ADOPTION MENU ----------------
    private static void adoptionMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Adoption Management ---");
            System.out.println("1. Insert Adoption");
            System.out.println("2. View All Adoptions");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> insertAdoption(sc);
                case 2 -> viewAdoptions();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void insertAdoption(Scanner sc) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Adopter ID: ");
        int adopterId = sc.nextInt();

        System.out.print("Enter Pet ID: ");
        int petId = sc.nextInt(); sc.nextLine();

        System.out.print("Enter adoption date (YYYY-MM-DD): ");
        String adoptionDate = sc.nextLine();


        Adopter adopter = session.get(Adopter.class, adopterId);
       Pet pet = session.get(Pet.class, petId);


        if (adopter == null || pet == null) {
            System.out.println("Invalid Adopter or Pet ID.");
            tx.rollback();
            session.close();
            return;
        }


        if ("Adopted".equalsIgnoreCase(pet.getStatus())) {
            System.out.println("Pet already adopted.");
            tx.rollback();
            session.close();
            return;
        }

        Adoption adoption = new Adoption();
        adoption.setAdopterId(adopterId);
        adoption.setPetId(petId);
        adoption.setAdoptionDate(LocalDate.parse(LocalDate.now().toString()));

        session.save(adoption);

        pet.setStatus("Adopted");
        session.update(pet);

        tx.commit();
        session.close();
        System.out.println("Adoption recorded successfully!");
    }

    private static void viewAdoptions() {
        Session session = factory.openSession();
        List<Adoption> adoptions = session.createQuery("from Adoption", Adoption.class).list();

        if (adoptions.isEmpty()) {
            System.out.println("No adoptions found!");
        } else {
            System.out.println("\n Adoption Records:");
            for (Adoption adoption : adoptions) {
                Adopter adopter = session.get(Adopter.class, adoption.getAdopterId());
                Pet pet = session.get(Pet.class, adoption.getPetId());

                System.out.println("Adoption ID: " + adoption.getId() +
                        ", Adopter: " + (adopter != null ? adopter.getName() : "Unknown") +
                        ", Pet: " + (pet != null ? pet.getName() : "Unknown") +
                        ", Date: " + adoption.getAdoptionDate());
            }
        }
        session.close();
    }
}
