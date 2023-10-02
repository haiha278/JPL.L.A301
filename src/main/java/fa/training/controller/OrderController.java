package fa.training.controller;

import fa.training.dao.imp.CustomerDaoImpl;
import fa.training.dao.imp.EmployeeDaoImpl;
import fa.training.dao.imp.OrderDaoImpl;
import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
    Scanner sc = new Scanner(System.in);
    CustomerDaoImpl customerDao = new CustomerDaoImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public void getAllOrdersByCustomerId() {
        while (true) {
            System.out.println("Enter customerID you want to get all orders");
            int id = sc.nextInt();
            if (customerDao.getCustomerByID(id) == null) {
                System.out.println("Customer not existed!");
                continue;
            }
            ArrayList<Order> orderList = orderDao.getAllOrdersByCustomerId(id);
            System.out.println("OrderID" + "\t\t" + "OrderDate" + "\t\t\t" + "Price");
            for (Order order : orderList) {
                System.out.println(order.getOrderID() + "\t\t\t" + order.getOrderDate() + "\t\t\t" + order.getLineItem().getPrice());
            }
            break;
        }
    }

    public void addOrder() {
        Order order = new Order();
        while (true) {
            try {
                System.out.println("Enter order id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (orderDao.getOrderByID(id) != null) {
                    System.out.println("ID existed !");
                    System.out.println("Please enter new ID");
                    continue;
                }
                order.setOrderID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            System.out.println("Enter order date: ");
            String date = sc.nextLine();
            LocalDate order_date = Validation.validDate(date, "dd/MM/yyyy");
            if (order_date == null) {
                continue;
            }
            order.setOrderDate(order_date);
            break;
        }

        while (true) {
            try {
                System.out.println("Enter customer id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (customerDao.getCustomerByID(id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again");
                    continue;
                }
                order.setCustomerID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            try {
                System.out.println("Enter employee id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (employeeDao.getEmployeeByID(id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again");
                    continue;
                }
                order.setEmployeeID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }
        boolean check = orderDao.addOrder(order);
        if (check == true) {
            System.out.println("Insert order successfully !");
        } else {
            System.out.println("Insert order fail !");
        }
    }
}
