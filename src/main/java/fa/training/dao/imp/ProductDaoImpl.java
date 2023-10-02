package fa.training.dao.imp;

import fa.training.dao.inter.ProductDAO;
import fa.training.entities.Product;
import fa.training.utils.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDAO {
    @Override
    public Product getProductByID(int id) {
        Product product = new Product();
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "select * from Product where product_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product.setProductID(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
