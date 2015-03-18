package DB;
import Model.*;

import java.util.ArrayList;

public interface IFDBInvoice {
    // get all employees
    public ArrayList<Invoice> getAllInvoice(boolean retriveAssociation);
    //find one employee having the name
    public Invoice searchInvoiceId( int id, boolean retriveAssociation);
    //insert a new employee
    public int insertInvoice(Invoice inv) throws Exception;
}