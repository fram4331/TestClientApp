package config;

import lombok.Getter;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    public static final String CONFIG_FILENAME = "./config/application.properties";

    static private String hostIP;

    static private int hostPort;

    static private String logPath;

    static private Properties properties;

    static public void initialize() {
        properties = new Properties();
        try {
            FileInputStream iStream = new FileInputStream(CONFIG_FILENAME);
            properties.load(iStream);
            hostIP 		= properties.getProperty("host.ip");
            hostPort 	= Integer.parseInt(properties.getProperty("host.port"));
            logPath     = properties.getProperty("log.path");

            iStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getHostIP() {
        return hostIP;
    }

    public static int getHostPort() {
        return hostPort;
    }

    public static String getLogPath() {
        return logPath;
    }
}
