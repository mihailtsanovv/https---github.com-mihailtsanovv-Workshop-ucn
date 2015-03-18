

package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBSupplier {
    // get all employees
    public ArrayList<Supplier> getAllSupplier(boolean retriveAssociation);
    //find one employee having the name
    public Supplier searchSupplierName( String name, boolean retriveAssociation);
    public Supplier searchSupplierId( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertSupplier(Supplier sup) throws Exception;
    //update information about an employee
    public int updateSupplier(Supplier sup);
    //delete
    public int deleteSupplier(int id);
    
}
