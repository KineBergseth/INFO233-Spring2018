package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.TextArea;
import model.addressData;
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
public class addressController implements Initializable {
    @FXML
    private TextField address_id;
    @FXML
    private TextField street_number;
    @FXML
    private TextField street_name;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField postal_town;
    @FXML
    public TextField address_idUpdate;
    @FXML
    public TextField street_numberUpdate;
    @FXML
    public TextField street_nameUpdate;
    @FXML
    public TextField postal_codeUpdate;
    @FXML
    public TextField postal_townUpdate;
    @FXML
    private TableView<addressData> addresstable;
    @FXML
    private TableColumn<addressData, Integer> address_idcolumn;
    @FXML
    private TableColumn<addressData, String> street_numbercolumn;
    @FXML
    private TableColumn<addressData, Integer> street_namecolumn;
    @FXML
    private TableColumn<addressData, String> postal_codecolumn;
    @FXML
    private TableColumn<addressData, String> postal_towncolumn;
    @FXML
    public TextArea resultArea;

    private ObservableList<addressData> data;
    private dbConnectionOblig DBc;
  
    public void initialize(URL url, ResourceBundle rb) {
        this.DBc = new dbConnectionOblig();
    }

    @FXML
    private void addAddress(ActionEvent event) {
        String sql = "INSERT INTO `address`(`address_id`, `street_number`, `street_name`, `postal_code`, `postal_town`) VALUES (? , ?, ?, ?, ?)";
        if (address_id.getText().length() == 0 || street_number.getText().length() == 0 || street_name.getText().length() == 0 || postal_code.getText().length() == 0 || postal_town.getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add category! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            if (con != null) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(this.address_id.getText()));
                stmt.setString(2, this.street_number.getText());
                stmt.setString(3, this.street_name.getText());
                stmt.setString(4, this.postal_code.getText());
                stmt.setString(5, this.postal_town.getText());
                stmt.execute();
                resultArea.setText("Address inserted! \n");
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
    private void updateAddress(ActionEvent event){
        String sql = "UPDATE `address`  SET street_number = ?, street_name = ?, postal_code = ?, postal_town = ?  WHERE address_id = ? ";
        if (address_idUpdate.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id before you can update category details! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, this.street_numberUpdate.getText());
            stmt.setString(2, this.street_nameUpdate.getText());
            stmt.setString(3, this.postal_codeUpdate.getText());
            stmt.setString(4, this.postal_townUpdate.getText());
            stmt.setInt(5, Integer.parseInt(this.address_idUpdate.getText()));
            resultArea.setText("Address updated! \n");
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
    private void loadAddressData(ActionEvent event) {
        try {
            Connection conn = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM address");
            while (rs.next()) {
                this.data.add(new addressData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            resultArea.setText("Address information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.address_idcolumn.setCellValueFactory(new PropertyValueFactory("address_id"));
        this.street_numbercolumn.setCellValueFactory(new PropertyValueFactory("street_number"));
        this.street_namecolumn.setCellValueFactory(new PropertyValueFactory("street_name"));
        this.postal_codecolumn.setCellValueFactory(new PropertyValueFactory("postal_code"));
        this.postal_towncolumn.setCellValueFactory(new PropertyValueFactory("postal_town"));

        this.addresstable.setItems(null);
        this.addresstable.setItems(this.data);
    }

    @FXML
    private void clearFieldsAdd(ActionEvent event) {
        this.address_id.setText("");
        this.street_number.setText("");
        this.street_name.setText("");
        this.postal_code.setText("");
        this.postal_town.setText("");
    }

    @FXML
    private void clearFieldsUpdate(ActionEvent event) {
        this.address_idUpdate.setText("");
        this.street_numberUpdate.setText("");
        this.street_nameUpdate.setText("");
        this.postal_codeUpdate.setText("");
        this.postal_townUpdate.setText("");
    }
}
