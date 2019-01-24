package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnectionOblig;
import model.invoiceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class invoiceController implements Initializable {
    @FXML
    private TextField invoice_id;
    @FXML
    private TextField customer;
    @FXML
    private DatePicker dato;
    @FXML
    public TextField invoice_idUpdate;
    @FXML
    public TextField customerUpdate;
    @FXML
    public DatePicker datoUpdate;
    @FXML
    private TableView<invoiceData> invoicetable;
    @FXML
    private TableColumn<invoiceData, Integer> invoice_idcolumn;
    @FXML
    private TableColumn<invoiceData, String> customercolumn;
    @FXML
    private TableColumn<invoiceData, String> datocolumn;
    @FXML
    public TextArea resultArea;

    private ObservableList<invoiceData> data;
    private dbConnectionOblig DBcon;
  
    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void addInvoice(ActionEvent event) {
        String sql = "INSERT INTO `invoice`(`invoice_id`, `customer`, `dato`) VALUES (? , ?, ?)";
        if (invoice_id.getText().length() == 0 || customer.getText().length() == 0 || dato.getEditor().getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add category! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt = null;
            if (con != null) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(this.invoice_id.getText()));
                stmt.setInt(2, Integer.parseInt(this.customer.getText()));
                stmt.setString(3, this.dato.getEditor().getText());
                stmt.execute();
                resultArea.setText("Invoice inserted! \n");
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
    private void updateInvoice(ActionEvent event){
        String sql = "UPDATE `invoice`  SET customer = ?, dato = ?  WHERE invoice_id = ? ";
        if (invoice_idUpdate.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id before you can update category details! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(this.customerUpdate.getText()));
            stmt.setString(2, this.datoUpdate.getEditor().getText());
            stmt.setInt(3, Integer.parseInt(this.invoice_idUpdate.getText()));
            resultArea.setText("Invoice updated! \n");
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
    private void loadInvoiceData(ActionEvent event) {
        try
        {
            Connection con = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();
      
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM invoice");
            while (rs.next()) {
                this.data.add(new invoiceData(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            resultArea.setText("Invoice information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.invoice_idcolumn.setCellValueFactory(new PropertyValueFactory("invoice_id"));
        this.customercolumn.setCellValueFactory(new PropertyValueFactory("customer"));
        this.datocolumn.setCellValueFactory(new PropertyValueFactory("dato"));

        this.invoicetable.setItems(null);
        this.invoicetable.setItems(this.data);
    }

    @FXML
    private void clearFieldsAdd(ActionEvent event) {
        this.invoice_id.setText("");
        this.customer.setText("");
        this.dato.setValue(null);
    }

    @FXML
    private void clearFieldsUpdate(ActionEvent event) {
        this.invoice_idUpdate.setText("");
        this.customerUpdate.setText("");
        this.datoUpdate.setValue(null);
    }
}
