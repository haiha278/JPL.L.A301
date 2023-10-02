package fa.training.dao.imp;

import fa.training.dao.inter.OrderDAO;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.utils.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAllOrdersByCustomerId(int id) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "select o.order_id,order_date,price from LineItem l join [Order] o on l.order_id = o.order_id\n" +
                "where o.customer_id = ?";
        try {
            Connection connection = ConnectDatabase.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setOrderDate(rs.getDate("order_date").toLocalDate());
                LineItem lineItem = new LineItem();
                lineItem.setPrice(rs.getDouble("price"));
                order.setLineItem(lineItem);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public Order getOrderByID(int id) {
        Order order = new Order();
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "select * from [Order] where order_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                order.setOrderID(rs.getInt("order_Id"));
                order.setOrderDate(rs.getDate("order_date").toLocalDate());
                order.setCustomerID(rs.getInt("customer_id"));
                order.setEmployeeID(rs.getInt("employee_id"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public boolean addOrder(Order order) {
        int row_effected;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "INSERT INTO [dbo].[Order]\n" +
                    "           ([order_id]\n" +
                    "           ,[order_date]\n" +
                    "           ,[customer_id]\n" +
                    "           ,[employee_id])\n" +
                    "     VALUES\n" +
                    "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, order.getOrderID());
            stm.setDate(2, Date.valueOf(order.getOrderDate()));
            stm.setInt(3, order.getCustomerID());
            stm.setInt(4, order.getEmployeeID());
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row_effected == 1;
    }
}
