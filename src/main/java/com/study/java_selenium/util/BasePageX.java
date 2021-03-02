package com.study.java_selenium.util;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;

public class BasePageX extends UIExcutorImpl {

    protected WebDriver driver;
    // 页面名称
    protected String pageName;
    // 页面元素配置文件路径
    protected String xmlPath;
    // 储蓄页面元素信息
    protected HashMap<String, Position> positionMap;

    protected LogUtil log = new LogUtil(BasePageX.class);

    Position position = null;

    public BasePageX(WebDriver driver, String pageName, String xmlName) throws Exception {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;
        xmlPath = this.getClass().getResource("").getPath() + xmlName;
        positionMap = XmlReadUtil.readXMLDocument(xmlPath, pageName);
        log.info("成功读取：" + pageName + "页面信息");
        Reporter.log("成功读取：" + pageName + "页面信息");
    }

    @Override
    public void switchWindow(String winTitle) {
        super.switchWindow(winTitle);
        log.info("切换窗口");
        Reporter.log("切换窗口" + winTitle);
    }

    @Override
    public void switchFrame(Position position) {
        super.switchFrame(position);
        log.info("切换frame至：" + position);
        Reporter.log("切换frame至：" + position);
    }

    // 使用Robot强制点击某处坐标，用于无法定位的元素， 比如(Object类型的元素)
    public void mouseMoveClick(int x, int y) throws AWTException {
        Robot rb1 = new Robot();
        rb1.mouseMove(x, y);
        rb1.delay(500);
        rb1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        rb1.delay(500);
        rb1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        rb1.delay(500);
        log.info("将鼠标移动至：" + "(" + x +"," + y + ")");
        Reporter.log("将鼠标移动至：" + "(" + x +"," + y + ")");
    }

    /**
     *  根据positionName返回对应的position
     */
    public Position getPosition(String positionName){
        Position position = null;
        if(positionMap != null){
            position = positionMap.get(positionName);
        }
        if(position == null){
            log.error("没有找到"+positionName+"页面元素");
            Reporter.log("没有找到"+positionName+"页面元素");
        }
        return position;
    }
}
