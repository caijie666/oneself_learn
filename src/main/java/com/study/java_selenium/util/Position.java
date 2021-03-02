package com.study.java_selenium.util;

import lombok.Data;

@Data
public class Position {

    // 路径
    private String path;
    // 位置名字，就是我们通常意义上叫的名字。例如：用户名输入框
    private String positionName;
    // 定位方式
    private ByType type;
    // 等待时间
    private int waitSec;

    // selenium的基本八种定位方式
    public enum ByType{
        xpath, id, name, className, cssSelector, tagName, partialLinkText, linkText
    }

    // 这种可以看作是针对iframe的情况
    public Position(String path, String positionName){
        this.path = path;
        this.positionName = positionName;
    }

    public Position(String path, ByType type, String positionName){
        this.path = path;
        this.type = type;
        this.positionName = positionName;
    }

    public Position(String path, int waitSec, ByType type, String positionName){
        this.path = path;
        this.waitSec = waitSec;
        this.positionName = positionName;
        this.type = type;
    }

}
