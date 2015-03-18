

package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBProduct {
    // get all employees
    public ArrayList<Product> getAllProduct(boolean retriveAssociation);
    //find one employee having the name
    public Product searchProductName( String name, boolean retriveAssociation);
    public Product searchProductBarcode( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertProduct(Product pro) throws Exception;
    //update information about an employee
    public int updateProduct(Product pro);
    //delete
    public int deleteProduct(int id);
    
}
