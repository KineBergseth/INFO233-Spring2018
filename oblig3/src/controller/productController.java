package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnectionOblig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.productData;

public class productController implements Initializable {
    @FXML
    private TextField product_id;
    @FXML
    private TextField product_name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField category;
    @FXML
    public TextField product_idUpdate;
    @FXML
    public TextField product_nameUpdate;
    @FXML
    public TextField descriptionUpdate;
    @FXML
    public TextField priceUpdate;
    @FXML
    public TextField categoryUpdate;
    @FXML
    private TableView<productData> producttable;
    @FXML
    private TableColumn<productData, Integer> product_idcolumn;
    @FXML
    private TableColumn<productData, String> product_namecolumn;
    @FXML
    private TableColumn<productData, String> descriptioncolumn;
    @FXML
    private TableColumn<productData, Integer> pricecolumn;
    @FXML
    private TableColumn<productData, Integer> categorycolumn;
    @FXML
    public TextArea resultArea;

    private ObservableList<productData> data;
    private dbConnectionOblig DBcon;
  
    public void initialize(URL url, ResourceBundle rb)
    {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void addProduct(ActionEvent event) {
        String sql = "INSERT INTO `product`(`product_id`, `product_name`, `description`, `price`, `category`) VALUES (? , ?, ?, ?, ?)";
        if (product_id.getText().length() == 0 || product_name.getText().length() == 0 || description.getText().length() == 0 || price.getText().length() == 0 || category.getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add category! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt = null;
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(this.product_id.getText()));
                stmt.setString(2, this.product_name.getText());
                stmt.setString(3, this.description.getText());
                stmt.setInt(4, Integer.parseInt(this.price.getText()));
                stmt.setInt(5, Integer.parseInt(this.category.getText()));
                stmt.execute();
                resultArea.setText("Product inserted! \n");
            con.close();
        } catch (SQLException e) {
            if(e.getMessage().contains("constraint")) {
                resultArea.setText("Constraint issue. One of the details already exists. \nPlease compare to the list of data and enter a valid input. \n" );
            } else {
                resultArea.setText("Error occurred while getting information from DB.\n" + e);
            }
        }
    }

    @FXML
    private void updateProduct(ActionEvent event){
        String sql = "UPDATE `product`  SET product_name = ?, description = ?, price = ?, category = ?  WHERE product_id = ? ";
        if (product_idUpdate.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id before you can update category details! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, this.product_nameUpdate.getText());
            stmt.setString(2, this.descriptionUpdate.getText());
            stmt.setInt(3, Integer.parseInt(this.priceUpdate.getText()));
            stmt.setInt(4, Integer.parseInt(this.categoryUpdate.getText()));
            stmt.setInt(5, Integer.parseInt(this.product_idUpdate.getText()));
            resultArea.setText("Product updated! \n");
            stmt.execute();
            con.close();
        } catch (SQLException e) {
            if(e.getMessage().contains("constraint")) {
                resultArea.setText("Constraint issue. One of the details already exists. \nPlease compare to the list of data and enter a valid input. \n" );
            } else {
                resultArea.setText("Error occurred while getting information from DB.\n" + e);
            }
        }
    }

    @FXML
    private void loadProductData(ActionEvent event) {
        try {
            Connection con = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();
      
            ResultSet rs = con.createStatement().executeQuery("SELECT product_id, product_name, description, price, category_name FROM product, category WHERE product.category = category.category_id");
            while (rs.next()) {
                this.data.add(new productData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
            resultArea.setText("Product information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.product_idcolumn.setCellValueFactory(new PropertyValueFactory("product_id"));
        this.product_namecolumn.setCellValueFactory(new PropertyValueFactory("product_name"));
        this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory("description"));
        this.pricecolumn.setCellValueFactory(new PropertyValueFactory("price"));
        this.categorycolumn.setCellValueFactory(new PropertyValueFactory("category"));
    
        this.producttable.setItems(null);
        this.producttable.setItems(this.data);
    }

    @FXML
    private void clearFieldsAdd(ActionEvent event) {
        this.product_id.setText("");
        this.product_name.setText("");
        this.description.setText("");
        this.price.setText("");
        this.category.setText("");
    }

    @FXML
    private void clearFieldsUpdate(ActionEvent event) {
        this.product_idUpdate.setText("");
        this.product_nameUpdate.setText("");
        this.descriptionUpdate.setText("");
        this.priceUpdate.setText("");
        this.categoryUpdate.setText("");
    }
}

