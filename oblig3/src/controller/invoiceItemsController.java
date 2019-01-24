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
import model.invoiceItemsData;

public class invoiceItemsController implements Initializable {
    @FXML
    private TextField invoice_id;
    @FXML
    private TextField product_id;
    @FXML
    private TableView<invoiceItemsData> invoiceItemstable;
    @FXML
    private TableColumn<invoiceItemsData, Integer> invoice_idcolumn;
    @FXML
    private TableColumn<invoiceItemsData, Integer> product_idcolumn;
    @FXML
    public TextArea resultArea;

    private ObservableList<invoiceItemsData> data;
    private dbConnectionOblig DBcon;
  
    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void addItem(ActionEvent event) {
        String sql = "INSERT INTO `invoice_items`(`invoice`, `product`) VALUES (? , ?)";
        if (invoice_id.getText().length() == 0 || product_id.getText().length() == 0) {
            resultArea.setText("You must fill inn all the fields before you can add items! \n");
            return;
        } try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(this.invoice_id.getText()));
            stmt.setInt(2, Integer.parseInt(this.product_id.getText()));
            resultArea.setText("Item inserted! \n");
            stmt.execute();
            con.close();
        } catch (SQLException e) {
                resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
    }

    //sletter alle tilfeller av valgt item som kunden har
    @FXML
    private void deleteItem(ActionEvent event) {
        String sql = "DELETE FROM `invoice_items` WHERE invoice = ? AND product = ? ";
        if (invoice_id.getText().length() == 0 || product_id.getText().length() == 0) {
            resultArea.setText("You must fill in a valid id and the item you want to delete! \n");
            return;
        }
        try {
            Connection con = dbConnectionOblig.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(this.invoice_id.getText()));
            stmt.setInt(2, Integer.parseInt(this.product_id.getText()));
            resultArea.setText("Item deleted! \n");
            stmt.execute();
            con.close();
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
    }


    @FXML
    private void loadInvoiceItemsData(ActionEvent event) {
        try
        {
            Connection con = dbConnectionOblig.getConnection();
            this.data = FXCollections.observableArrayList();
      
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM invoice_items");
            while (rs.next()) {
                this.data.add(new invoiceItemsData(rs.getInt(1), rs.getInt(2)));
            }
            resultArea.setText("InvoiceItems information loaded! \n");
        } catch (SQLException e) {
            resultArea.setText("Error occurred while getting information from DB.\n" + e);
        }
        this.invoice_idcolumn.setCellValueFactory(new PropertyValueFactory("invoice_id"));
        this.product_idcolumn.setCellValueFactory(new PropertyValueFactory("product_id"));

        this.invoiceItemstable.setItems(null);
        this.invoiceItemstable.setItems(this.data);
    }

    @FXML
    private void clearFields(ActionEvent event) {
        this.invoice_id.setText("");
        this.product_id.setText("");
    }

}
