package com.lgz.crazy.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by lgz on 2019/2/22.
 */
public class SysUtil {
    private final static Logger logger = LoggerFactory.getLogger(SysUtil.class);
    static Hashtable tb = new Hashtable();
    static String resourcefilePosition = "";
    static String jdbcfilePosition = "";
    static String datasourceconfigPath = "";
    static Properties mailProperties = null;
    static Properties FTPProperties = null;
    static String mailconfigPath = "";
    static String FTPconfigPath = "";

    public static String getDatasourceconfigPath() {
        return datasourceconfigPath;
    }

    public static void setDatasourceconfigPath(String datasourceconfigPath) {
        SysUtil.datasourceconfigPath = datasourceconfigPath;
    }

    public static String getPropertiesValue(String key, String filefullname) {
        String value = "";
        try {
            if (filefullname != null && !filefullname.equals("") && filefullname.lastIndexOf("/") != -1) {
                String filename = filefullname.substring(filefullname.lastIndexOf("/") + 1, filefullname.length());
                if (tb.keySet().contains(filename)) {
                    value = ((Properties) tb.get(filename)).getProperty(key);
                } else {
                    Properties properties = getProperties(filefullname);
                    value = properties.getProperty(key);
                    tb.put(filename, properties);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }

    public static Properties getProperties(String filefullname) {
        try {
            File file = new File(filefullname);
            FileInputStream fis = null;
            Properties properties = new Properties();
            fis = new FileInputStream(file);
            properties.load(fis);
            return properties;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getResourceFilePosition() {
        if (resourcefilePosition.equals("")) {
            String classPath = SysUtil.class.getClassLoader().getResource(
                    SysUtil.class.getName().replace('.', '/') + ".class").getFile();
            String classRoot = "";
            classRoot = classPath.substring(0, classPath.indexOf("/com/zuche/common/util"));
            if (!classRoot.equals("") && classRoot.startsWith("/") && classRoot.indexOf(":") == 2)
                classRoot = classRoot.substring(1);
            if (!classRoot.equals(""))
                resourcefilePosition = classRoot + "/resource.properties";
        }
        return resourcefilePosition;
    }

    public static String getDatasoureConfigPath() {
        if (datasourceconfigPath.equals("")) {
            String classPath = SysUtil.class.getClassLoader().getResource(
                    SysUtil.class.getName().replace('.', '/') + ".class").getFile();
            String classRoot = "";
            classRoot = classPath.substring(0, classPath.indexOf("/com/zuche/common/util"));
            if (!classRoot.equals("") && classRoot.startsWith("/") && classRoot.indexOf(":") == 2)
                classRoot = classRoot.substring(1);
            if (!classRoot.equals(""))
                datasourceconfigPath = classRoot + "/datasourceconfig.xml";
        }
        return datasourceconfigPath;
    }

    public static String getMailConfigPath() {
        if (mailconfigPath.equals("")) {
            String classPath = SysUtil.class.getClassLoader().getResource(
                    SysUtil.class.getName().replace('.', '/') + ".class").getFile();
            String classRoot = "";
            classRoot = classPath.substring(0, classPath.indexOf("/com/zuche/common/util"));
            if (!classRoot.equals("") && classRoot.startsWith("/") && classRoot.indexOf(":") == 2)
                classRoot = classRoot.substring(1);
            if (!classRoot.equals(""))
                mailconfigPath = classRoot + "/mail.properties";
        }
        return mailconfigPath;
    }

    public static Properties getmailProperties() {
        if (mailProperties == null)
            mailProperties = getProperties(getMailConfigPath());
        return mailProperties;
    }


    public static String getFTPConfigPath() {
        if (FTPconfigPath.equals("")) {
            String classPath = SysUtil.class.getClassLoader().getResource(
                    SysUtil.class.getName().replace('.', '/') + ".class").getFile();
            String classRoot = "";
            classRoot = classPath.substring(0, classPath.indexOf("/com/zuche/common/util"));
            if (!classRoot.equals("") && classRoot.startsWith("/") && classRoot.indexOf(":") == 2)
                classRoot = classRoot.substring(1);
            if (!classRoot.equals(""))
                FTPconfigPath = classRoot + "/ftp.properties";
        }
        return FTPconfigPath;
    }

    public static Properties getFTPProperties() {
        if (FTPProperties == null)
            FTPProperties = getProperties(getFTPConfigPath());
        return FTPProperties;
    }
}
