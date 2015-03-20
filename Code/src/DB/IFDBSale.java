package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBSale {
    // get all employees
    public ArrayList<Sale> getAllSale(boolean retriveAssociation);
    //find one employee having the name
    public Sale searchSaleId( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertSale(Sale sale) throws Exception;
    //update information about an employee
    public int updateSale(Sale sale);
    //delete
    public int deleteSale(int id);
    
}