package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class customerData
{
  private final IntegerProperty customer_id;
  private final StringProperty customer_name;
  private final IntegerProperty address;
  private final StringProperty phone_number;
  private final StringProperty billing_account;
  
  public customerData(Integer cusId, String cusName, Integer addr, String phoneNo, String billingAcc)
  {
    this.customer_id = new SimpleIntegerProperty(cusId);
    this.customer_name = new SimpleStringProperty(cusName);
    this.address = new SimpleIntegerProperty(addr);
    this.phone_number = new SimpleStringProperty(phoneNo);
    this.billing_account = new SimpleStringProperty(billingAcc);
  }
  
  public Integer getcustomer_id()
  {
    return this.customer_id.get();
  }
  
  public String getcustomer_name()
  {
    return this.customer_name.get();
  }
  
  public Integer getaddress()
  {
    return this.address.get();
  }
  
  public String getphone_number()
  {
    return this.phone_number.get();
  }
  
  public String getbilling_account()
  {
    return this.billing_account.get();
  }
  
  public void setcustomer_id(Integer value)
  {
    this.customer_id.set(value);
  }
  
  public void setcustomer_name(String value)
  {
    this.customer_name.set(value);
  }
  
  public void setaddress(Integer value)
  {
    this.address.set(value);
  }
  
  public void setphone_number(String value)
  {
    this.phone_number.set(value);
  }
  
  public void setbilling_account(String value)
  {
    this.billing_account.set(value);
  }
  
  public IntegerProperty customer_idProperty()
  {
    return this.customer_id;
  }
  
  public StringProperty customer_nameProperty()
  {
    return this.customer_name;
  }
  
  public IntegerProperty addressProperty()
  {
    return this.address;
  }
  
  public StringProperty phone_numberProperty()
  {
    return this.phone_number;
  }
  
  public StringProperty billing_accountProperty()
  {
    return this.billing_account;
  }
}
