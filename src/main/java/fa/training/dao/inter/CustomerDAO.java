package fa.training.dao.inter;

import fa.training.entities.Customer;

import java.sql.Connection;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<Customer> getAllCustomer();

    public Customer getCustomerByID(int id);

    public boolean addCustomer(Customer customer);

    public boolean deleteCustomer(int id);

    public boolean updateCustomer(Customer customer);
}
