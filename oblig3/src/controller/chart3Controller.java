package controller;

import dbUtil.dbConnectionOblig;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import model.productData;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class chart3Controller implements Initializable {

    @FXML
    public ScatterChart scatterChart;
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

            xAxis.setLabel("Products");
            yAxis.setLabel("Price");
            XYChart.Series series = new XYChart.Series();
            series.setName("item price");

            ResultSet rs = con.createStatement().executeQuery("SELECT product_name, price FROM product");
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }
            scatterChart.setTitle("Price per product");
            scatterChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

