package fa.training.entities;

import java.time.LocalDate;

public class Customer {
    private int customerID;
    private String customerName;
    private LocalDate dob;

    public Customer() {
    }

    public Customer(int customerID, String customerName, LocalDate dob) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.dob = dob;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
