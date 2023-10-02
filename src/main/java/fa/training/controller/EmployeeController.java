package fa.training.controller;

import fa.training.dao.imp.EmployeeDaoImpl;
import fa.training.entities.Employee;
import fa.training.utils.Validation;

import java.util.Scanner;

public class EmployeeController {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    Scanner sc = new Scanner(System.in);
    Validation validation = new Validation();

    public void addEmployee() {
        Employee employee = new Employee();
        while (true) {
            try {
                System.out.println("Enter employee id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (employeeDao.getEmployeeByID(id) != null) {
                    System.out.println("ID existed !");
                    System.out.println("Please enter new ID");
                    continue;
                }
                employee.setEmployeeID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            System.out.println("Enter employee name: ");
            String name = sc.nextLine();
            if (!validation.isValidName(name)) {
                continue;
            }
            employee.setEmployeeName(name);
            break;
        }

        while (true) {
            try {
                System.out.println("Enter employee salary: ");
                double salary = Double.parseDouble(sc.nextLine());
                if (salary < 0) {
                    System.out.println("Salary must higher than 0");
                    System.out.println("Please enter new ID");
                    continue;
                }
                employee.setSalary(salary);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            System.out.println("Do you have manager (Yes/No):");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("No")) {
                employee.setManagerID(0);
                break;
            }
            if (input.equalsIgnoreCase("Yes")) {
                try {
                    System.out.println("Enter employee's managerID:");
                    int manaID = Integer.parseInt(sc.nextLine());

                    if (employeeDao.getEmployeeByID(manaID) == null) {
                        System.out.println("ID not existed !");
                        System.out.println("Please enter new ID");
                        continue;
                    }
                    employee.setManagerID(manaID);
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("ID must be number");
                    continue;
                }
            }
        }
        boolean check = employeeDao.addEmployee(employee);
        if (check == true) {
            System.out.println("Insert employee successfully !");
        } else {
            System.out.println("Insert employee fail !");
        }
    }
}
