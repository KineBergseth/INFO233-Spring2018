import dbUtil.dbConnectionOblig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Main extends Application {

    private static BorderPane root = new BorderPane();

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // loading FXML resources
        URL menuBarUrl = getClass().getResource("view/master.fxml");
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource("view/address.fxml");
        AnchorPane paneOne = FXMLLoader.load( paneOneUrl );


        // constructing scene
        root.setTop(bar);
        root.setCenter(paneOne);

        //obs! hvis du har liten skjerm så er det en load knapp og textarea nederst til venstre på sidene
        //Scene scene = new Scene(root, 1500, 930);
        Scene scene = new Scene(root, 1000, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        dbConnectionOblig.createDB();
        launch(args);
    }
}
