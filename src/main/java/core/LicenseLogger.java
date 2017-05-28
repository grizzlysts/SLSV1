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

        try(Connection conn = DriverManager.getConnection(Config.getProp("dburl"), Config.getProp("user"), Config.getProp("password"))) {
            PreparedStatement stmt = conn.prepareStatement("insert into licenselog (licensekey, url, version, entrydate) values (?,?,?,?)");
            stmt.setString(1, license.getLicenseKey());
            stmt.setString(2, license.getUrl());
            stmt.setString(3, license.getVersion());
            stmt.setDate(4, license.getDate());
            boolean success = stmt.execute();
            System.out.println("Log Entry Added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
