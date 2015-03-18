

package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBCustomer {
    // get all employees
    public ArrayList<Customer> getAllCustomer(boolean retriveAssociation);
    //find one employee having the name
    public Customer searchCustomerName( String name, boolean retriveAssociation);
    public Customer searchCustomerId( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertCustomer(Customer cust) throws Exception;
    //update information about an employee
    public int updateCustomer(Customer cust);
    //delete
    public int deleteCustomer(int id);
    
}
