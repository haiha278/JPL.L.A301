package fa.training.controller;

import fa.training.dao.imp.CustomerDaoImpl;
import fa.training.dao.imp.LineItemDaoImpl;
import fa.training.dao.imp.OrderDaoImpl;
import fa.training.dao.imp.ProductDaoImpl;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.entities.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class LineItemController {
    Scanner sc = new Scanner(System.in);
    OrderDaoImpl orderDao = new OrderDaoImpl();
    LineItemDaoImpl lineItemDao = new LineItemDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();
    CustomerDaoImpl customerDao = new CustomerDaoImpl();

    public void getAllItemsByOrderId() {
        while (true) {
            System.out.println("Enter order ID you want to get items:");
            int id = sc.nextInt();
            if (orderDao.getOrderByID(id) == null) {
                System.out.println("Order not eixsted");
                continue;
            }
            ArrayList<LineItem> lineItems = lineItemDao.getAllItemsByOrderId(id);
            System.out.println("OrderID" + "\t\t" + "Product Name" + "\t\t" + "Quantity");
            for (LineItem lineItem : lineItems) {
                System.out.println(lineItem.getOrderID() + "\t\t\t" + lineItem.getProduct().getProductName() + "\t\t\t\t\t" + lineItem.getQuantity());
            }
            break;
        }
    }

    public void addLineItem() {
        LineItem lineItem = new LineItem();
        Product product = new Product();
        while (true) {
            try {
                System.out.println("Enter order id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (orderDao.getOrderByID(id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again ID");
                    continue;
                }
                lineItem.setOrderID(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            try {
                System.out.println("Enter product id: ");
                int product_id = Integer.parseInt(sc.nextLine());
                if (productDao.getProductByID(product_id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again ID");
                    continue;
                }
                lineItem.setProductID(product_id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }

        while (true) {
            try {
                System.out.println("Enter quantity: ");
                int quantity = Integer.parseInt(sc.nextLine());
                lineItem.setQuantity(quantity);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Quantity must be number");
            }
        }
        product = productDao.getProductByID(lineItem.getProductID());
        double price = product.getPrice() * lineItem.getQuantity();
        lineItem.setPrice(price);
        boolean check = lineItemDao.addLineItem(lineItem);
        if (check == true) {
            System.out.println("Insert line items successfully !");
        } else {
            System.out.println("Insert line items fail !");
        }
    }

    public void getOrderTotalSum() {
        while (true) {
            try {
                System.out.println("Enter customer id: ");
                int id = Integer.parseInt(sc.nextLine());
                if (customerDao.getCustomerByID(id) == null) {
                    System.out.println("ID not existed !");
                    System.out.println("Please enter again ID");
                    continue;
                }
                System.out.println("Total money have to pay: " + lineItemDao.getOrderTotalSum(id));
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be number");
            }
        }
    }
}
