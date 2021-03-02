package com.study.java_selenium.util;

import org.openqa.selenium.WebElement;

public interface UIExcutor {

    // 点击
    void click(Position position) throws Exception;
    // 输入文本
    void sendKey(Position position, String value) throws Exception;
    // 获取元素文本
    String getText(Position position) throws Exception;
    // 获取元素
    WebElement getElement(Position position) throws Exception;
    // 判断元素是否显示
    boolean isElementDisplayed(Position position) throws Exception;
    // 切换页面
    void switchWindow(String winTitle);
    // 切换frame
    void switchFrame(Position position);
    // 智能等待
    void waitElement(Position position, int sec);
    // 获取弹窗的文字
    String getAlertText();
    // 获取元素属性
    String getAttribute(Position position, String attributeName) throws Exception;
    // javaScript 强制点击
    void jsClick(Position position) throws Exception;

}
