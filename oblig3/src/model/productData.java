package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class productData
{
  private final IntegerProperty product_id;
  private final StringProperty product_name;
  private final StringProperty description;
  private final IntegerProperty price;
  private final StringProperty category;
  
  public productData(Integer proId, String proName, String desc, Integer pri, String cat)
  {
    this.product_id = new SimpleIntegerProperty(proId);
    this.product_name = new SimpleStringProperty(proName);
    this.description = new SimpleStringProperty(desc);
    this.price = new SimpleIntegerProperty(pri);
    this.category = new SimpleStringProperty(cat);
  }
  
  public Integer getproduct_id()
  {
    return this.product_id.get();
  }
  
  public String getproduct_name()
  {
    return this.product_name.get();
  }
  
  public String getdescription()
  {
    return this.description.get();
  }
  
  public Integer getprice()
  {
    return this.price.get();
  }
  
  public String getcategory()
  {
    return this.category.get();
  }
  
  public void setProduct_id (Integer value)
  {
    this.product_id.set(value);
  }
  
  public void setproduct_name(String value)
  {
    this.product_name.set(value);
  }
  
  public void setdescription(String value)
  {
    this.description.set(value);
  }
  
  public void setprice(Integer value)
  {
    this.price.set(value);
  }
  
  public void setcategory(String value)
  {
    this.category.set(value);
  }
  
  public IntegerProperty product_idProperty()
  {
    return this.product_id;
  }
  
  public StringProperty product_nameProperty()
  {
    return this.product_name;
  }
  
  public StringProperty descriptionProperty()
  {
    return this.description;
  }
  
  public IntegerProperty priceProperty()
  {
    return this.price;
  }
  
  public StringProperty categoryProperty()
  {
    return this.category;
  }
}

