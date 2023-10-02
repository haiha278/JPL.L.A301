package fa.training.dao.imp;

import fa.training.dao.inter.EmployeeDAO;
import fa.training.entities.Employee;
import fa.training.utils.ConnectDatabase;

import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDAO {
    @Override
    public boolean addEmployee(Employee employee) {
        int row_effected;
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "INSERT INTO [dbo].[Employee]\n" +
                    "           ([employee_id]\n" +
                    "           ,[employee_name]\n" +
                    "           ,[salary]\n" +
                    "           ,[manager_id])\n" +
                    "     VALUES\n" +
                    "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, employee.getEmployeeID());
            stm.setString(2, employee.getEmployeeName());
            stm.setDouble(3, employee.getSalary());
            if (employee.getManagerID() == 0) {
                stm.setNull(4, Types.INTEGER);
            } else if (employee.getManagerID() != 0) {
                stm.setInt(4, employee.getManagerID());
            }
            row_effected = stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row_effected == 1;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Employee employee = new Employee();
        try {
            Connection connection = ConnectDatabase.getConnection();
            String sql = "select * from Employee where employee_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setEmployeeName(rs.getString("employee_name"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setManagerID(rs.getInt("manager_id"));
            } else if (!rs.next()) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
}
