package core;

import server.License;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by bobcowher on 12/31/16.
 */
public class LicenseLogger {

    private static String user;
    private static String password;
    private static String url;

    public static void addLicenseEntry(License license){

        PreparedStatement stmt = null;
        Connection conn = null;

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./db.properties"));
            user = props.getProperty("user");
            password = props.getProperty("password");
            url = props.getProperty("dburl");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement("insert into licenselog (licensekey, url, version, entrydate) values (?,?,?,?)");
            stmt.setString(1, license.getLicenseKey());
            stmt.setString(2, license.getUrl());
            stmt.setString(3, license.getVersion());
            stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            boolean success = stmt.execute();
            System.out.println("Log Entry Added");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

}
