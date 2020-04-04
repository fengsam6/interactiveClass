package com.code.classgenerator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author
 */
public class PropertiesUtil {

    private String properiesName = "";

    public  static PropertiesUtil newPropertiesUtilInstance(String properiesName) {
        return new PropertiesUtil(properiesName);
    }

    public PropertiesUtil(String fileName) {
        this.properiesName = fileName;
    }

    /**
     * 按key获取值
     *
     * @param key
     * @return
     */
    public String readProperty(String key) {
        String value = "";
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
            Properties p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 获取整个配置信息
     *
     * @return
     */
    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }



    public static void main(String[] args) {
        // sysConfig.properties(配置文件)
        PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
        System.out.println(p.getProperties().get("db.url"));
        System.out.println(p.readProperty("db.url"));
        System.exit(0);
    }

}