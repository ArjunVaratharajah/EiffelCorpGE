package org.gustaveeiffel.fr.eiffelcorp.common.customer;

public class Customer {

    private int id;
    private String firstname;
    private String lastname;
    private double budget;

    public Customer(int id, String firstname, String lastname, double budget) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public double getBudget() {
        return budget;
    }

    public String getInfo() {
        return "ID: " + id + " | Firstname: " + getFirstname() + " | Lastname: " + getLastname() + " | Budget: "
                + getBudget();
    }

    public void removeFromBudget(double price) {
        budget -= price;
    }

    public void addToBudget(double price) {
        budget += price;
    }

}
