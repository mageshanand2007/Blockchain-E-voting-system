package com.evoting.model;

// INHERITANCE: Admin extends Person, inheriting its fields and behavior
public class Admin extends Person {

    public Admin(int id, String name, String username, String password) {
        // Calls the parent (Person) constructor
        super(id, name, username, password);
    }

    // POLYMORPHISM / METHOD OVERRIDING: Admin provides its own version
    // of the abstract method displayRole() declared in Person
    @Override
    public void displayRole() {
        System.out.println("Role: Administrator");
    }
}
