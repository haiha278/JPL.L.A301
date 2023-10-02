package fa.training.dao.imp;

import fa.training.dao.inter.CustomerDAO;
import fa.training.entities.Customer;
import fa.training.utils.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "select customer_ID,customer_Name,date_of_birth from Customer";
        try {
            Connection connection = ConnectDatabase.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customer_ID"));
                customer.setCustomerName(rs.getString("customer_Name"));
                customer.setDob(rs.getDate("date_of_birth").toLocalDate());
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Customer getCustomerByID(int id) {
        Customer customer = new Customer();
        String sql = "select * from Customer where customer_ID = ?";
        try {
            Connection connection = ConnectDatabase.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                customer.setCustomerID(rs.getInt("customer_ID"));
                customer.setCustomerName(rs.getString("customer_Name"));
                customer.setDob(rs.getDate("date_of_birth").toLocalDate());
            } else if (!rs.next()) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        int row_effected;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "INSERT INTO [Customer]\n" +
                    "           ([customer_ID]\n" +
                    "           ,[customer_Name]\n" +
                    "           ,[date_of_birth])\n" +
                    "     VALUES\n" +
                    "           (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, customer.getCustomerID());
            stm.setString(2, customer.getCustomerName());
            stm.setDate(3, Date.valueOf(customer.getDob()));
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row_effected == 1;
    }

    @Override
    public boolean deleteCustomer(int id) {
        int row_effected;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "delete LineItem from LineItem l left join [Order] o on l.order_id = o.order_id where o.customer_id = ?\n" +
                    "delete from [Order] where customer_id = ?\n" +
                    "delete from Customer where customer_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            stm.setInt(3, id);
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row_effected == 1;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        int row_effected;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "UPDATE [dbo].[Customer]\n" +
                    "   SET [customer_Name] = ?\n" +
                    "      ,[date_of_birth] = ?\n" +
                    " WHERE customer_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, customer.getCustomerName());
            stm.setDate(2, Date.valueOf(customer.getDob()));
            stm.setInt(3, customer.getCustomerID());
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row_effected == 1;
    }
}
