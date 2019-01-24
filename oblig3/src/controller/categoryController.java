package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import model.categoryData;
import dbUtil.dbConnectionOblig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class categoryController implements Initializable {
    @FXML
    private TextField category_id;
    @FXML
    private TextField category_name;
    @FXML
    public TextField category_idUpdate;
    @FXML
    public TextField category_nameUpdate;
    @FXML
    private TableView<categoryData> categorytable;
    @FXML
    private TableColumn<categoryData, Integer> category_idcolumn;
    @FXML
    private TableColumn<categoryData, String> category_namecolumn;
    @FXML
    private TextArea resultArea;

    private ObservableList<categoryData> data;
    private dbConnectionOblig DBcon;
  
    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void addCategory(ActionEvent event){
        String sql = "INSERT INTO `category`(`category_id`, `category_name`) VALUES (? , ?)";
        if (category_id.getText().length() == 0 || category_name.getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add category! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            if (con != null) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(this.category_id.getText()));
                stmt.setString(2, this.category_name.getText());
                stmt.execute();
                resultArea.setText("Category inserted! \n");
            }
            con.close();
        } catch (SQLException e) {
            if(e.getMessage().contains("constraint")) {
                resultArea.setText("Constraint issue. Id or name already exists. \nPlease compare to the list of data and enter a valid input. \n" );
            } else {
                resultArea.setText("Error occurred while getting information from DB.\n" + e);
            }
        }
    }

    @FXML
    private void UpdateCategory(ActionEvent event) {
        String sql = "UPDATE `category`  SET category_name = ?  WHERE category_id = ? ";
        if (category_idUpdate.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id before you can update category details! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            if (con != null) {
                stmt = con.prepareStatement(sql);
                if (category_nameUpdate.getText().length() > 0){
                    stmt.setString(1, this.category_nameUpdate.getText());
                    stmt.setInt(2, Integer.parseInt(this.category_idUpdate.getText()));
                    resultArea.setText("Category updated! \n");
                    stmt.execute();
                }
                else {
                    resultArea.setText("you must enter a name if you want to change it! \n");
                }
            }
            con.close();
        } catch (SQLException e) {
            if(e.getMessage().contains("constraint")) {
                resultArea.setText("Constraint issue. Name already exists. \nPlease compare to the list of data and enter a valid input. \n" );
            } else {
                resultArea.setText("Error occurred while getting information from DB.\n" + e);
            }
        }
    }

    @FXML
    private void loadCategoryData(ActionEvent event){
        try {
            Connection con = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();
      
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM category");
            while (rs.next()) {
                this.data.add(new categoryData(rs.getInt(1), rs.getString(2)));
            }
            resultArea.setText("Category information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.category_idcolumn.setCellValueFactory(new PropertyValueFactory("category_id"));
        this.category_namecolumn.setCellValueFactory(new PropertyValueFactory("category_name"));

        this.categorytable.setItems(null);
        this.categorytable.setItems(this.data);
    }

    @FXML
    private void clearFieldsAdd(ActionEvent event) {
        this.category_id.setText("");
        this.category_name.setText("");
    }

    @FXML
    private void clearFieldsUpdate(ActionEvent event) {
        this.category_idUpdate.setText("");
        this.category_nameUpdate.setText("");
    }
}
