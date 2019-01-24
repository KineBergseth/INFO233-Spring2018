package controller;
import dbUtil.dbConnectionOblig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class chart2Controller implements Initializable {
    @FXML
    public BarChart<String, Number> barChart;
    @FXML
    public CategoryAxis xAxis;
    @FXML
    public NumberAxis yAxis;
    private dbConnectionOblig DBcon;

    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();
        Connection con;
        try {
            con = dbConnectionOblig.getConnection();
            xAxis.setLabel("Customer");
            yAxis.setLabel("No of invoices");

            XYChart.Series<String, Number> series = new XYChart.Series();
            series.setName("invoice per customer");

            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(invoice_id), customer_name FROM invoice, customer WHERE invoice.customer = customer.customer_id GROUP BY customer_name");
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
            }
            barChart.setTitle("Invoices per customer");
            barChart.getData().addAll(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}