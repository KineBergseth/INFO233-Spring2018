import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class masterController {
    @FXML
    public MenuItem displayAddress;
    @FXML
    public MenuItem displayCategory;
    @FXML
    public MenuItem displayCustomer;
    @FXML
    public MenuItem displayInvoice;
    @FXML
    public MenuItem displayProduct;
    @FXML
    public MenuItem displayInvoiceItems;
    @FXML
    public MenuItem displayShowInvoice;
    @FXML
    public MenuItem displayChart1;
    @FXML
    public MenuItem displayChart2;
    @FXML
    public MenuItem displayChart3;


    @FXML
    void switchToAddress(ActionEvent event) {

        try {
            URL addressUrl = getClass().getResource("view/address.fxml");
            AnchorPane address = FXMLLoader.load( addressUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToCategory(ActionEvent event) {
        try {
            URL categoryUrl = getClass().getResource("view/category.fxml");
            AnchorPane category = FXMLLoader.load( categoryUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToCustomer(ActionEvent event) {
        try {
            URL customerUrl = getClass().getResource("view/customer.fxml");
            AnchorPane customer = FXMLLoader.load( customerUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToInvoice(ActionEvent event) {
        try {
            URL invoiceUrl = getClass().getResource("view/invoice.fxml");
            AnchorPane invoice = FXMLLoader.load( invoiceUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(invoice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToProduct(ActionEvent event) {
        try {
            URL productUrl = getClass().getResource("view/product.fxml");
            AnchorPane product = FXMLLoader.load( productUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToShowInvoiceItems(ActionEvent event) {
        try {
            URL InvoiceItemsUrl = getClass().getResource("view/InvoiceItems.fxml");
            AnchorPane InvoiceItems = FXMLLoader.load( InvoiceItemsUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(InvoiceItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToShowInvoice(ActionEvent event) {
        try {
            URL showInvoiceUrl = getClass().getResource("view/showInvoice.fxml");
            AnchorPane showInvoice = FXMLLoader.load( showInvoiceUrl );
            BorderPane border = Main.getRoot();
            border.setCenter(showInvoice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToChart1(ActionEvent event) {
        try {
            URL chart1Url = getClass().getResource("view/chart1.fxml");
            AnchorPane chart = FXMLLoader.load( chart1Url );
            BorderPane border = Main.getRoot();
            border.setCenter(chart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToChart2(ActionEvent event) {
        try {
            URL chart2Url = getClass().getResource("view/chart2.fxml");
            AnchorPane chart = FXMLLoader.load( chart2Url );
            BorderPane border = Main.getRoot();
            border.setCenter(chart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToChart3(ActionEvent event) {
        try {
            URL chart3Url = getClass().getResource("view/chart3.fxml");
            AnchorPane chart = FXMLLoader.load( chart3Url );
            BorderPane border = Main.getRoot();
            border.setCenter(chart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
