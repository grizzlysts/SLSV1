package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by bobcowher on 1/1/17.
 */
public class Config {

    private static String user;
    private static String password;
    private static String url;
    private static Properties props = new Properties();
    private static final String propFile = "./db.properties";


    public static String getProp(String property) {
        try {
            props.load(new FileInputStream(propFile));
            return props.getProperty(property);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "default" + property;

    }

}
