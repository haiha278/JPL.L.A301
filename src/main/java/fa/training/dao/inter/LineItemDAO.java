package fa.training.dao.inter;

import fa.training.entities.LineItem;

import javax.sound.sampled.Line;
import java.util.ArrayList;

public interface LineItemDAO {
    public ArrayList<LineItem> getAllItemsByOrderId(int id);

    public boolean addLineItem(LineItem lineItem);

    public double getOrderTotalSum(int id);
}
