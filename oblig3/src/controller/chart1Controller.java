package controller;

import dbUtil.dbConnectionOblig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class chart1Controller implements Initializable {

    @FXML
    public PieChart pieChart;
    private dbConnectionOblig DBcon;
    private ObservableList<PieChart.Data> pieChartData;

    public void initialize(URL url, ResourceBundle rb) {
        this.DBcon = new dbConnectionOblig();

        Connection con;
        try {
            con = dbConnectionOblig.getConnection();
            this.pieChartData = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(product_id), category_name FROM product, category WHERE product.category = category.category_id GROUP BY category_name");
            while (rs.next()) {
                int count = rs.getInt(1);
                this.pieChartData.add(new PieChart.Data(rs.getString(2) + ": " + count, rs.getInt(1)));
            }
            pieChart.setTitle("Products per category");
            pieChart.setLegendSide(Side.LEFT);
            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}