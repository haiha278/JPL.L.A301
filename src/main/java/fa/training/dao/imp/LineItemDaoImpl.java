package fa.training.dao.imp;

import fa.training.dao.inter.LineItemDAO;
import fa.training.entities.LineItem;
import fa.training.entities.Product;
import fa.training.utils.ConnectDatabase;

import javax.sound.sampled.Line;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LineItemDaoImpl implements LineItemDAO {
    @Override
    public ArrayList<LineItem> getAllItemsByOrderId(int id) {
        ArrayList<LineItem> lineItems = new ArrayList<>();
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "select l.*, p.product_name from LineItem l join Product p \n" +
                    "on l.product_id = p.product_id where order_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderID(rs.getInt("order_id"));
                lineItem.setProductID(rs.getInt("product_id"));
                lineItem.setQuantity(rs.getInt("quantity"));
                lineItem.setPrice(rs.getDouble("price"));
                Product product = new Product();
                product.setProductName(rs.getString("product_name"));
                lineItem.setProduct(product);
                lineItems.add(lineItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lineItems;
    }

    @Override
    public boolean addLineItem(LineItem lineItem) {
        int row_effected = 0;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "INSERT INTO [dbo].[LineItem]\n" +
                    "           ([order_id]\n" +
                    "           ,[product_id]\n" +
                    "           ,[quantity]\n" +
                    "           ,[price])\n" +
                    "     VALUES\n" +
                    "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lineItem.getOrderID());
            stm.setInt(2, lineItem.getProductID());
            stm.setInt(3, lineItem.getQuantity());
            stm.setDouble(4, lineItem.getPrice());
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Duplicate key");;
        }
        return row_effected == 1;
    }

    @Override
    public double getOrderTotalSum(int id) {
        double total = 0;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "select customer_id, SUM(price) as total from LineItem l join [Order] o \n" +
                    "on l.order_id = o.order_id where customer_id = ? group by customer_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
