package dbUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class dbConnectionOblig {
    private static Connection con = null;

    public static Connection getConnection()
            throws SQLException
    {
        try {
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection("jdbc:sqlite:invoice.sqlite");
        } catch (ClassNotFoundException|SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public static void createDB() throws SQLException {
        String DBFilePath = "invoice.sqlite";
        String SchemaFilePath = "schema.sql";
        String dburl = "jdbc:sqlite:" + DBFilePath;

        File file = new File(DBFilePath);
        if (file.exists() && !file.isDirectory()) {
            con = DriverManager.getConnection(dburl);

        } else {
            try {
                byte[] encoded = Files.readAllBytes(Paths.get(SchemaFilePath));
                String input = new String(encoded);

                List<String> ii = Arrays.asList(input.split(";"));
                con = DriverManager.getConnection(dburl);
                for (Integer i = 0; i < (ii.size() - 1); i++) {
                    String aa = ii.get(i);
                    System.out.println(aa);
                    con.prepareStatement(aa).execute();
                }
                System.out.println("successfully added database from schema file!");

            } catch (Exception E) {
                E.printStackTrace();
            }
        }

    }
}
