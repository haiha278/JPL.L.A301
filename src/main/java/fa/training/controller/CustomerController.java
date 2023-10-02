package fa.training.controller;

import fa.training.dao.imp.CustomerDaoImpl;
import fa.training.entities.Customer;
import fa.training.utils.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {
    Scanner sc = new Scanner(System.in);
    CustomerDaoImpl customerDao = new CustomerDaoImpl();
    Validation validation = new Validation();

    public void getAllCustomers() {
        ArrayList<Customer> customerList = customerDao.getAllCustomer();
        System.out.println("CustomerID" + "\t\t" + "CustomerName" + "\t\t" + "Date of Birth");
        for (Customer c : customerList) {
            System.out.println(c.getCustomerID() + "\t\t\t\t" + c.getCustomerName() + "\t\t\t\t" + c.getDob());
        }
    }

    public void addCustomer() {
        Customer customer = new Customer();
        while (true) {
            try {
                System.out.println("Enter customer id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (customerDao.getCustomerByID(id) != null) {
                    System.out.println("ID existed !");
                    System.out.println("Please enter new ID");
                    continue;
                }
                customer.setCustomerID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            System.out.println("Enter customer name: ");
            String name = sc.nextLine();
            if (!validation.isValidName(name)) {
                continue;
            }
            customer.setCustomerName(name);
            break;
        }

        while (true) {
            System.out.println("Enter date of birth: ");
            String date = sc.nextLine();
            LocalDate dob = Validation.validDate(date, "dd/MM/yyyy");
            if (dob == null) {
                continue;
            }
            customer.setDob(dob);
            break;
        }
        boolean check = customerDao.addCustomer(customer);
        if (check == true) {
            System.out.println("Insert customer successfully !");
        } else {
            System.out.println("Insert customer fail !");
        }
    }

    public void deleteCustomer() {
        while (true) {
            try {
                System.out.println("Enter customer id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (customerDao.getCustomerByID(id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again ID");
                    continue;
                }
                boolean check = customerDao.deleteCustomer(id);
                if (check == true) {
                    System.out.println("Delete customer successfully !");
                } else {
                    System.out.println("Delete customer fail !");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }
    }

    public void updateCustomer() {
        Customer customer = new Customer();
        while (true) {
            try {
                System.out.println("Enter customer id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (customerDao.getCustomerByID(id) == null) {
                    System.out.println("Customer not existed !");
                    System.out.println("Please enter again: ");
                    continue;
                }
                customer.setCustomerID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            System.out.println("Enter customer name: ");
            String name = sc.nextLine();
            if (!validation.isValidName(name)) {
                continue;
            }
            customer.setCustomerName(name);
            break;
        }

        while (true) {
            System.out.println("Enter date of birth: ");
            String date = sc.nextLine();
            LocalDate dob = Validation.validDate(date, "dd/MM/yyyy");
            if (dob == null) {
                continue;
            }
            customer.setDob(dob);
            break;
        }
        boolean check = customerDao.updateCustomer(customer);
        if (check == true) {
            System.out.println("Update customer successfully !");
        } else {
            System.out.println("Update customer fail !");
        }
    }
}
