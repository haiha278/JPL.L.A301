package fa.training.entities;

import java.time.LocalDate;

public class Order {
    private int orderID;
    private LocalDate orderDate;
    private int customerID;
    private int employeeID;
    private double total;

    private LineItem lineItem;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order(int orderID, LocalDate orderDate, int customerID, int employeeID, double total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.total = total;
    }

    public Order() {
    }

    public Order(int orderID, LocalDate orderDate, int customerID, int employeeID, double total, LineItem lineItem) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.total = total;
        this.lineItem = lineItem;
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
