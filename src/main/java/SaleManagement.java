import fa.training.controller.CustomerController;
import fa.training.controller.EmployeeController;
import fa.training.controller.LineItemController;
import fa.training.controller.OrderController;

import java.util.Scanner;

public class SaleManagement {

    private static void showMenu() {
        System.out.println("============= Sale Management =============");
        System.out.println("1. Get all customers");
        System.out.println("2. Get all orders");
        System.out.println("3. Get line items by order id");
        System.out.println("4. Add a customer");
        System.out.println("5. Delete a customer");
        System.out.println("6. Update customer");
        System.out.println("7. Add a employee");
        System.out.println("8. Add an order");
        System.out.println("9. Add line item");
        System.out.println("10. Get total money");
        System.out.println("11. Exit");
    }

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        OrderController orderController = new OrderController();
        LineItemController lineItemController = new LineItemController();
        EmployeeController employeeController = new EmployeeController();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 11) {
            showMenu();
            try {
                System.out.println("Enter your choice: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        customerController.getAllCustomers();
                        break;
                    case 2:
                        orderController.getAllOrdersByCustomerId();
                        break;
                    case 3:
                        lineItemController.getAllItemsByOrderId();
                        break;
                    case 4:
                        customerController.addCustomer();
                        break;
                    case 5:
                        customerController.deleteCustomer();
                        break;
                    case 6:
                        customerController.updateCustomer();
                        break;
                    case 7:
                        employeeController.addEmployee();
                        break;
                    case 8:
                        orderController.addOrder();
                        break;
                    case 9:
                        lineItemController.addLineItem();
                        break;
                    case 10:
                        lineItemController.getOrderTotalSum();
                        break;
                    case 11:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            }
        }
    }
}
