package com.hz.school.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * 系统配置的集中入口
 */
public class SSMConfig {

    private static Logger log = Logger.getLogger(SSMConfig.class);

    private static Properties pro = new Properties();

    private SSMConfig(){}
    static {
        try{
            InputStream is = SSMConfig.class.getResourceAsStream("/ssm-config.xml");
            pro.loadFromXML(is);
            String defaultDisk = pro.getProperty("default.disk");
            Set set = pro.keySet();
            for(Object key : set){
                String keyStr = key+"";
                String value = pro.getProperty(keyStr);
                if(keyStr.equals(defaultDisk))continue;
                File folder = new File(defaultDisk, value);
                String newValue = folder.getAbsolutePath();
                pro.put(key, newValue);
            }
        }catch (IOException ex){
            throw new RuntimeException("SSM Config init fail.",ex);
        }
    }

    public static Properties getProperties(){
        return pro;
    }

    public static String getProperty(String key){
        return pro.getProperty(key);
    }


}
