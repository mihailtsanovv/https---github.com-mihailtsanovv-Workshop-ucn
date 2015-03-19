package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBPartSale {
    // get all employees
    public ArrayList<PartSale> getAllPartSaleBySaleId(int saleId, boolean retriveAssociation);
    //insert a new employee
    public int insertPartSale(PartSale part) throws Exception;
}