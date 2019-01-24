package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class addressData
{
  private final IntegerProperty address_id;
  private final StringProperty street_number;
  private final StringProperty street_name;
  private final StringProperty postal_code;
  private final StringProperty postal_town;
  
  public addressData(Integer addressId, String streetNumber, String streetName, String postalCode, String postalTown)
  {
    this.address_id = new SimpleIntegerProperty(addressId);
    this.street_number = new SimpleStringProperty(streetNumber);
    this.street_name = new SimpleStringProperty(streetName);
    this.postal_code = new SimpleStringProperty(postalCode);
    this.postal_town = new SimpleStringProperty(postalTown);
  }
  
  public Integer getaddress_id()  {    return this.address_id.get();  }
  
  public String getstreet_number()
  {
    return this.street_number.get();
  }
  
  public String getstreet_name()
  {
    return this.street_name.get();
  }
  
  public String getpostal_code()
  {
    return this.postal_code.get();
  }
  
  public String getpostal_town()
  {
    return this.postal_town.get();
  }
  
  public void setaddress_id(Integer value)
  {
    this.address_id.set(value);
  }
  
  public void setstreet_number(String value)
  {
    this.street_number.set(value);
  }
  
  public void setstreet_name(String value)
  {
    this.street_name.set(value);
  }
  
  public void setpostal_code(String value)
  {
    this.postal_code.set(value);
  }
  
  public void setpostal_town(String value)
  {
    this.postal_town.set(value);
  }
  
  public IntegerProperty address_idProperty()
  {
    return this.address_id;
  }
  
  public StringProperty street_numberProperty()
  {
    return this.street_number;
  }
  
  public StringProperty street_nameProperty()  {    return this.street_name;  }

  public StringProperty postal_codeProperty()
  {
    return this.postal_code;
  }
  
  public StringProperty postal_townProperty()
  {
    return this.postal_town;
  }
}
