package com.cj.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 接口请求测试的父类
public class TestBase {
    public Properties prop;

    // 构造方法
    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                    + "/src/main/java/com/cj/config/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
