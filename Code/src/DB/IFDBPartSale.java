package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBPartSale {
    // get all employees
    public ArrayList<PartSale> getAllPartSale(boolean retriveAssociation);
    //find one employee having the name
    public PartSale searchPartSaleId( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertPartSale(PartSale part) throws Exception;
}