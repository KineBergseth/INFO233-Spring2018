package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.TextArea;
import model.customerData;
import dbUtil.dbConnectionOblig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class customerController implements Initializable {
    @FXML
    private TextField customer_id;
    @FXML
    private TextField customer_name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField billing_account;
    @FXML
    public TextField customer_idUpdate;
    @FXML
    public TextField customer_nameUpdate;
    @FXML
    public TextField addressUpdate;
    @FXML
    public TextField phone_numberUpdate;
    @FXML
    public TextField billing_accountUpdate;
    @FXML
    private TableView<customerData> customertable;
    @FXML
    private TableColumn<customerData, Integer> customer_idcolumn;
    @FXML
    private TableColumn<customerData, String> customer_namecolumn;
    @FXML
    private TableColumn<customerData, Integer> addresscolumn;
    @FXML
    private TableColumn<customerData, String> phone_numbercolumn;
    @FXML
    private TableColumn<customerData, String> billing_accountcolumn;
    @FXML
    public TextArea resultArea;

    private ObservableList<customerData> data;
    private dbConnectionOblig DBcon;
  
    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void addCustomer(ActionEvent event) {
        String sql = "INSERT INTO `customer`(`customer_id`, `customer_name`, `address`, `phone_number`, `billing_account`) VALUES (? , ?, ?, ?, ?)";
        if (customer_id.getText().length() == 0 || customer_name.getText().length() == 0 || address.getText().length() == 0 || phone_number.getText().length() == 0 || billing_account.getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add category! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt = null;
            if (con != null) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(this.customer_id.getText()));
                stmt.setString(2, this.customer_name.getText());
                stmt.setInt(3, Integer.parseInt(this.address.getText()));
                stmt.setString(4, this.phone_number.getText());
                stmt.setString(5, this.billing_account.getText());
                stmt.execute();
                resultArea.setText("Customer inserted! \n");
            }
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
    private void updateCustomer(ActionEvent event) throws SQLException {
        String sql = "UPDATE `customer`  SET customer_name = ?, address = ?, phone_number = ?, billing_account = ?  WHERE customer_id = ? ";
        if (customer_idUpdate.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id before you can update category details! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, this.customer_nameUpdate.getText());
            stmt.setInt(2, Integer.parseInt(this.addressUpdate.getText()));
            stmt.setString(3, this.phone_numberUpdate.getText());
            stmt.setString(4, this.billing_accountUpdate.getText());
            stmt.setInt(5, Integer.parseInt(this.customer_idUpdate.getText()));
            resultArea.setText("Customer updated! \n");
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
    private void loadCustomerData(ActionEvent event) {
        try {
            Connection conn = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();
      
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                this.data.add(new customerData(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
            resultArea.setText("Customer information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.customer_idcolumn.setCellValueFactory(new PropertyValueFactory("customer_id"));
        this.customer_namecolumn.setCellValueFactory(new PropertyValueFactory("customer_name"));
        this.addresscolumn.setCellValueFactory(new PropertyValueFactory("address"));
        this.phone_numbercolumn.setCellValueFactory(new PropertyValueFactory("phone_number"));
        this.billing_accountcolumn.setCellValueFactory(new PropertyValueFactory("billing_account"));
    
        this.customertable.setItems(null);
        this.customertable.setItems(this.data);
    }

    @FXML
    private void clearFieldsAdd(ActionEvent event) {
        this.customer_id.setText("");
        this.customer_name.setText("");
        this.address.setText("");
        this.phone_number.setText("");
        this.billing_account.setText("");
    }

    @FXML
    private void clearFieldsUpdate(ActionEvent event) {
        this.customer_idUpdate.setText("");
        this.customer_nameUpdate.setText("");
        this.addressUpdate.setText("");
        this.phone_numberUpdate.setText("");
        this.billing_accountUpdate.setText("");
    }
}
