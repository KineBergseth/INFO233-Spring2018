package controller;
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

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class showInvoiceController implements Initializable {
    @FXML
    public TextField invoice_idInput;
    @FXML
    public TextArea resultArea;
    @FXML
    public TextField invoice_id;
    @FXML
    public TextField invoice_date;
    @FXML
    public TextField customer_id;
    @FXML
    public TextField customer_name;
    @FXML
    public TextField street_name;
    @FXML
    public TextField street_number;
    @FXML
    public TextField postal_code;
    @FXML
    public TextField postal_town;
    @FXML
    public TextField totalItems;
    @FXML
    public TextField totalSum;

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

    private ObservableList<productData> data;

    private dbConnectionOblig DBcon;

    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
    }

    @FXML
    private void loadInvoice (ActionEvent event) {
        if (invoice_idInput.getText().length() == 0 ) {
            resultArea.setText("You must enter id to search! \n");
        } try{
            Connection con = dbConnectionOblig.getConnection();
            String input;
            input = (this.invoice_idInput.getText());


            ResultSet invoiceTable = con.createStatement().executeQuery("SELECT customer, dato FROM invoice WHERE invoice_id = "+input+"");
            ResultSet addressTable = con.createStatement().executeQuery("SELECT customer_name, street_number, street_name, postal_code, postal_town FROM invoice, customer, address WHERE invoice_id = "+input+" AND invoice.customer = customer.customer_id AND customer.address = address.address_id");
            ResultSet tItems = con.createStatement().executeQuery("SELECT COUNT(product) FROM invoice_items WHERE invoice = "+input+"");
            ResultSet tPrice = con.createStatement().executeQuery("SELECT SUM(price) FROM invoice_items, product WHERE invoice = "+input+" AND invoice_items.product = product.product_id");

            int cID =invoiceTable.getInt(1);
            String date = invoiceTable.getString(2);
            String cName = addressTable.getString(1);
            String sNumber = addressTable.getString(2);
            String sName = addressTable.getString(3);
            String pCode = addressTable.getString(4);
            String pTown = addressTable.getString(5);

            int totItems = tItems.getInt(1);
            int totSum = tPrice.getInt(1);

            invoice_id.setText(input);
            invoice_date.setText(date);
            customer_id.setText(String.valueOf(cID));
            customer_name.setText(cName);
            street_name.setText(sName);
            street_number.setText(sNumber);
            postal_code.setText(pCode);
            postal_town.setText(pTown);
            totalItems.setText(String.valueOf(totItems));
            totalSum.setText(String.valueOf(totSum) + " kr");
            resultArea.setText("Invoice information loaded! \n");


            this.data = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT product_id, product_name, description, price, category_name  FROM invoice_items, product, category WHERE invoice = "+input+" AND invoice_items.product = product.product_id AND product.category = category.category_id");
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
}

