package fa.training.dao.inter;

import fa.training.entities.Order;

import java.util.ArrayList;

public interface OrderDAO {

    public ArrayList<Order> getAllOrdersByCustomerId(int id);

    public Order getOrderByID(int id);

    public boolean addOrder(Order order);
}
