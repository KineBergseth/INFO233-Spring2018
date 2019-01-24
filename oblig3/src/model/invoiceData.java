package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class invoiceData
{
  private final IntegerProperty invoice_id;
  private final IntegerProperty customer;
  private final StringProperty dato;

  public invoiceData(Integer invoiceId, Integer cus, String d)
  {
    this.invoice_id = new SimpleIntegerProperty(invoiceId);
    this.customer = new SimpleIntegerProperty(cus);
    this.dato = new SimpleStringProperty(d);
  }
  
  public Integer getinvoice_id()
  {
    return this.invoice_id.get();
  }
  
  public Integer getcustomer()
  {
    return this.customer.get();
  }
  
  public String getdato()
  {
    return this.dato.get();
  }
  
  public void setinvoice_id(Integer value)
  {
    this.invoice_id.set(value);
  }
  
  public void setcustomer(Integer value)
  {
    this.customer.set(value);
  }
  
  public void setdato(String value)
  {
    this.dato.set(value);
  }
  
  public IntegerProperty invoice_idProperty()
  {
    return this.invoice_id;
  }
  
  public IntegerProperty customerProperty()
  {
    return this.customer;
  }
  
  public StringProperty datoProperty()
  {
    return this.dato;
  }
  
}
