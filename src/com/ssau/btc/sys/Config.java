package com.ssau.btc.sys;

import com.ssau.btc.model.ActivationFunctionType;
import com.ssau.btc.model.LayerInfo;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Author: Sergey42
 * Date: 15.02.14 15:26
 */
public class Config {

    protected static Config instance;
    protected Properties properties;

    protected static final String PATH = "D:\\btc.conf";

    static {
        instance = new Config();
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(PATH));
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            instance.properties = new Properties();
            instance.properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    protected Config() {
    }

    public static void flush() {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(new File(PATH));
            PrintWriter printWriter = new PrintWriter(stream);
            instance.properties.store(printWriter, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    public static final int MAX_LAYER_NEURON_CNT = 100;

    public static final String DEFAULT_ERA_CNT = "10";
    public static final String DEFAULT_TEACH_COEFF = "0.02";
    public static final String MIN_DATE_FROM = "2012-01-01";
    public static final int MAX_TEACH_CYCLE_COUNT = 100;
    public static final String DEFAULT_FORECAST_SIZE = "10";

    public static final String DIRECTORY = "D:\\";

    public static int CMEANS_MAX_ITERATIONS = 20;
    public static double CMEANS_ERROR_BARRIER = 0.001;

    public static final int TOTAL_BTC = 21_000_000;

    public static List<LayerInfo> getDefaultStructure() {
        List<LayerInfo> items = new ArrayList<>();
        items.add(new LayerInfo(8, null, 0));
        items.add(new LayerInfo(16, ActivationFunctionType.R_SIGMOID, 0.15));
        items.add(new LayerInfo(1, ActivationFunctionType.SINUS, 0.85));
        return items;
    }

    public static LayerInfo createLayerInfo() {
        return new LayerInfo(10, null, 0.8);
    }

    public static String getLocale() {
        return instance.properties.getProperty(ConfigKeys.LANGUAGE);
    }

    public static void setLocale(String locale) {
        instance.properties.setProperty(ConfigKeys.LANGUAGE, locale);
    }

    public static String getDbUrl() {
        return instance.properties.getProperty(ConfigKeys.DB_URL);
    }

    public static void setDbUrl(String url) {
        instance.properties.setProperty(ConfigKeys.DB_URL, url);
    }

    public static String getDbUser() {
        return instance.properties.getProperty(ConfigKeys.DB_USER);
    }

    public static void setDbUser(String user) {
        instance.properties.setProperty(ConfigKeys.DB_USER, user);
    }

    public static String getDbPass() {
        return instance.properties.getProperty(ConfigKeys.DB_PASS);
    }

    public static void setDbPass(String pass) {
        instance.properties.setProperty(ConfigKeys.DB_PASS, pass);
    }

    public static boolean useDemo() {
        return Boolean.valueOf(instance.properties.getProperty(ConfigKeys.USE_DEMO));
    }

    public static double getDemoStep() {
        try {
            return Double.valueOf(instance.properties.getProperty(ConfigKeys.DEMO_STEP));
        } catch (NumberFormatException e) {
            return 0.1;
        }
    }

    public static int getDemoSize() {
        try {
            return Integer.valueOf(instance.properties.getProperty(ConfigKeys.DEMO_SIZE));
        } catch (NumberFormatException e) {
            return 314 * 4 / 10;
        }
    }

    public static int get24HourIndexInterval() {
        try {
            Integer i = Integer.valueOf(instance.properties.getProperty(ConfigKeys.INDEX_24_HOUR_INTERVAL));
            return i > 1 && i < 60 ? i : 10;
        } catch (NumberFormatException e) {
            return 10;
        }
    }

    public static boolean getUseTotalBtc() {
        return Boolean.valueOf(instance.properties.getProperty(ConfigKeys.USE_TOTAL_BTC));
    }
}
