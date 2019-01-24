package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class invoiceItemsData
{
  private final IntegerProperty invoice_id;
  private final IntegerProperty product_id;

  public invoiceItemsData(Integer invoiceId, Integer cus)
  {
    this.invoice_id = new SimpleIntegerProperty(invoiceId);
    this.product_id = new SimpleIntegerProperty(cus);
  }
  
  public Integer getinvoice_id()
  {
    return this.invoice_id.get();
  }
  
  public Integer getproduct_id()
  {
    return this.product_id.get();
  }

  public void setinvoice_id(Integer value)
  {
    this.invoice_id.set(value);
  }
  
  public void setproduct_id(Integer value)
  {
    this.product_id.set(value);
  }

  public IntegerProperty invoice_idProperty()
  {
    return this.invoice_id;
  }
  
  public IntegerProperty product_idProperty()
  {
    return this.product_id;
  }
}

