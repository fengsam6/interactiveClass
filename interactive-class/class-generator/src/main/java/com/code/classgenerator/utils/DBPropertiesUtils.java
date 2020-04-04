package com.code.classgenerator.utils;


import java.util.Properties;

/**
 * @author
 */
public class DBPropertiesUtils {
//.setUrl(dbUrl)
//                .setUsername("root")
//                .setPassword("")
//                .setDriverName("com.mysql.jdbc.Driver");
   private static String dbFile= "gen.properties";
    private static PropertiesUtil propertiesUtil;
    private static Properties properties;
   static {
        propertiesUtil = PropertiesUtil.newPropertiesUtilInstance(dbFile);
       properties=propertiesUtil.getProperties();
   }
    public  static String getDBUrl(){
        return  properties.getProperty("jdbc.url","root");
    }
   public  static String getDBUsername(){
      return  properties.getProperty("jdbc.user","root");
   }

    public  static String getDBPassword(){
        return   properties.getProperty("jdbc.pwd","");
    }

    public  static String getDBDriverName(){
        return   properties.getProperty("jdbc.driver","com.mysql.jdbc.Driver");
    }

}