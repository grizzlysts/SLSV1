package core;

import server.License;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by bobcowher on 1/1/17.
 */
public class DataAggregator {

    private static ArrayList<License> licenseList = new ArrayList<License>();

    public static void refreshLicenseList(){

        PreparedStatement stmt = null;

        try(Connection conn = DriverManager.getConnection(Config.getProp("dburl"), Config.getProp("user"), Config.getProp("password"))){

            stmt = conn.prepareStatement("select * from licenselog");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                License license = new License(rs.getLong("id") ,rs.getString("licensekey"), rs.getString("url"), rs.getString("version"), rs.getDate("entrydate"));
                licenseList.add(license);
                license = null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static ArrayList<License> getUniqueLicenses(){
//
//        ArrayList<License> uniqueLicenseList = new ArrayList<>();
//
//        for (License license: licenseList) {
//
//            if(uniqueLicenseList.size() > 0) {
//
//                System.out.println(uniqueLicenseList);
//                if(uniqueLicenseList.indexOf(license) > 0){
//                    continue;
//                } else {
//                    uniqueLicenseList.add(license);
//                }
//
//            } else uniqueLicenseList.add(license);
//        }
//
//        return uniqueLicenseList;
//    }

    public static void printLicenses(){

        if(licenseList.size() < 1){
            refreshLicenseList();
        }

//
//        for (License license: getUniqueLicenses()) {
//            System.out.println(license.getUrl());
//        }
    }



}
