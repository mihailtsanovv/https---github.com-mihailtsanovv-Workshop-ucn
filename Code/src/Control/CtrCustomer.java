

package Control;
import Model.*;
import DB.*;

import java.util.ArrayList;
/**
 * CtrCustomer.java
 * @author kbh
 * @version 5. oktober 2006
 * @version 2011.11.02 change to illustrate transaction
 * @version 2012.02.12 changed to eclipse
 */
public class CtrCustomer {
   
    
    /** Creates a new instance of CtrCustomer */
    public CtrCustomer() {
        
    }
    public ArrayList<Customer> findAllCustomers()
    {
      IFDBCustomer dbCust = new DBCustomer();
      ArrayList<Customer> allCust = new ArrayList<Customer>();
      allCust = dbCust.getAllCustomer(false);
      return allCust;
    }
    public Customer findByName(String name)
    {
        IFDBCustomer dbCust = new DBCustomer();
        return dbCust.searchCustomerName(name, true);
    }
      public Customer findById(int id)
    {
        IFDBCustomer dbCust = new DBCustomer();
        return dbCust.searchCustomerId( id, true);
    }
      
      public int updateCust(int id,String name, String address, int zipcode, String city, String phone)
      {
          IFDBCustomer dbCust = new DBCustomer();
          Customer cust = new Customer();

          cust.setName(name);
          cust.setAddress(address);
          cust.setCity(city);
          cust.setZipCode(zipcode);
          cust.setPhone(phone);
          return  dbCust.updateCustomer(cust);
          
          
      }
     
      public  void insertCust(String name, String address, int zipcode, String city, String phone) throws Exception
      {    
           Customer custObj = new Customer();
//           custObj.setId(id);
           custObj.setName(name);
           custObj.setAddress(address);
           custObj.setCity(city);
           custObj.setZipCode(zipcode);
           custObj.setPhone(phone);
    
           try{
            DBConnection.startTransaction();
            DBCustomer dbCust = new DBCustomer();
            dbCust.insertCustomer(custObj);
            DBConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DBConnection.rollbackTransaction();
               throw new Exception("Customer not inserted");
           }
      }
      

      
      
      
    
}
