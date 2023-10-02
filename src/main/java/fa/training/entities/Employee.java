package fa.training.entities;

public class Employee {
    private int employeeID;
    private String employeeName;
    private double salary;
    private int managerID;

    public Employee() {
    }

    public Employee(int employeeID, String employeeName, double salary, int managerID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.salary = salary;
        this.managerID = managerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
}
