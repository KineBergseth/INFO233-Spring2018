package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class categoryData
{
  private final IntegerProperty category_id;
  private final StringProperty category_name;

  public categoryData(Integer categoryId, String categoryName)
  {
    this.category_id = new SimpleIntegerProperty(categoryId);
    this.category_name = new SimpleStringProperty(categoryName);

  }
  
  public Integer getcategory_id()
  {
    return this.category_id.get();
  }
  
  public String getcategory_name()
  {
    return this.category_name.get();
  }
  
  public void setcategory_id(Integer value)
  {
    this.category_id.set(value);
  }
  
  public void setcategory_name(String value)
  {
    this.category_name.set(value);
  }

  public IntegerProperty category_idProperty()
  {
    return this.category_id;
  }
  
  public StringProperty category_nameProperty()
  {
    return this.category_name;
  }

}
