package com.study.java_selenium.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.Set;

public class UIExcutorImpl implements UIExcutor {

    private WebDriver driver;
    static LogUtil logs = new LogUtil(UIExcutor.class);

    public UIExcutorImpl(WebDriver driver){
        this.driver = driver;
    }

    //这里对接口中的方法进行重写，意味着当我们执行这些方法的时候，可以将过程步骤作为log记录下来，并加入report中
    @Override
    public void click(Position position) throws Exception {
        WebElement element = getElement(position);
        element.click();
        logs.info("click元素：" + position.getPositionName() + "["
                + "By." + position.getType() + ":" + position.getPath() + "] success！");
        // 在report中显示相应的log
        Reporter.log("click元素：" + position.getPositionName() + "["
                + "By." + position.getType() + ":" + position.getPath() + "] success！");
    }

    @Override
    public void sendKey(Position position, String value) throws Exception {
        WebElement ele = getElement(position);
        ele.sendKeys(value);
        logs.info("input输入："+position.getPositionName()+"["+"By."+position.getType()+":"+position.getPath()+"  value:"+value+"]");
        Reporter.log("input输入："+position.getPositionName()+"["+"By."+position.getType()+":"+position.getPath()+"  value:"+value+"]");
    }

    @Override
    public String getText(Position position) throws Exception {
        WebElement ele = getElement(position);
        String txt = ele.getText();
        return txt;
    }

    @Override
    public WebElement getElement(Position position) throws Exception {
        WebElement ele = null;
        String path = position.getPath();
        // 这里私人指定的时间是3秒等待，用于页面元素的加载，需要根据实际情况进行设置
        WebDriverWait wait = new WebDriverWait(driver, 3);
        logs.info("查找元素："+position.getPositionName()+"["+"By."+position.getType()+":"+path+"]");
        Reporter.log("查找元素："+position.getPositionName()+"["+"By."+position.getType()+":"+path+"]");
        // 使用switch-case分支语句来分别对应selenium八种定位方式
        switch(position.getType()) {
            case xpath:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(path)));
                    ele = driver.findElement(By.xpath(path));
                }catch (Exception e) {
                    logs.error("findElment ByXpath:" + path + "-failed! NoSuchElement");
                    Reporter.log("findElment ByXpath:" + path + "-failed! NoSuchElement");
                }
                break;
            case id:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(path)));
                    ele = driver.findElement(By.id(path));
                }catch (Exception e) {
                    logs.error("findElement ById" + path + "-failed! NoSuchELement");
                    Reporter.log("findElement ById" + path + "-failed! NoSuchELement");
                }
                break;
            case className:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(path)));
                    ele = driver.findElement(By.className(path));
                }catch (Exception e) {
                    logs.error("findElement ByClassName" + path + "-failed! NoSuchElement");
                    Reporter.log("findElement ByClassName" + path + "-failed! NoSuchElement");
                }
                break;
            case linkText:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(path)));
                    ele = driver.findElement(By.linkText(path));
                }catch (Exception e) {
                    logs.error("findElement ByLinkText" + path + "-failed! NoSuchElement");
                    Reporter.log("findElement ByLinkText" + path + "-failed! NoSuchElement");
                }
                break;
            case name:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(path)));
                    ele = driver.findElement(By.name(path));
                }catch (Exception e) {
                    logs.error("findElement ByName" + path + "-failed! NoSuchElement");
                    Reporter.log("findElement ByName" + path + "-failed! NoSuchElement");
                }
                break;
            case cssSelector:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(path)));
                    ele = driver.findElement(By.cssSelector(path));
                }catch (Exception e) {
                    logs.error("findElement ByCssSelector" + path + "-failed! NoSuchElement");
                    Reporter.log("findElement ByCssSelector" + path + "-failed! NoSuchElement");
                }
            case tagName:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(path)));
                    ele = driver.findElement(By.tagName(path));
                }catch (Exception e) {
                    logs.error("findElement ByTagName" + path + "-failed ! NoSuchElement");
                    Reporter.log("findElement ByTagName" + path + "-failed ! NoSuchElement");
                }
            case partialLinkText:
                try {
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(path)));
                    ele = driver.findElement(By.partialLinkText(path));
                }catch (Exception e) {
                    logs.error("findElement By partialLinkText" + path + "-failed ! NoSuchElement");
                    Reporter.log("findElement By partialLinkText" + path + "-failed ! NoSuchElement");
                }

            default:
                break;
        }
        return ele;
    }

    @Override
    public boolean isElementDisplayed(Position position) throws Exception {
        WebElement ele = getElement(position);
        boolean flag = ele.isDisplayed();
        return flag;
    }

    @Override
    public void switchWindow(String winTitle) {
        logs.info("切换windows窗口:" + winTitle);
        Reporter.log("切换windows窗口:" + winTitle);
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(driver.getWindowHandle())) {
                continue;
            } else {
                driver.switchTo().window(handle);
                if (winTitle.contains(driver.getTitle())) {
                    break;
                } else {
                    continue;
                }
            }
        }
    }

    @Override
    public void switchFrame(Position position) {
        driver.switchTo().frame(position.getPath());
    }

    @Override
    public void waitElement(Position position , int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        String add = position.getPath();
        switch (position.getType()) {
            case id:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(add)));
                break;
            case xpath:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case name:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case linkText:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case className:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case tagName:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case partialLinkText:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            case cssSelector:
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(add)));
                break;
            default:
                break;
        }
    }

    @Override
    public String getAlertText() {
        String alertText = "";
        try {
            // webdriver对弹框的处理略坑，所以先等待2s再切换到弹框，否则可能无法切换
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
        } catch (NoSuchElementException e) {
            logs.error("no alert open,switch to alert failed");
            Reporter.log("no alert open,switch to alert failed");
        }
        return alertText;
    }

    @Override
    //获取元素属性值（提供属性名，比如要获取什么属性（比如：value），这里就要输入什么的值）
    public String getAttribute(Position position , String attributeName) throws Exception {
        WebElement ele = getElement(position);
        String value = ele.getAttribute(attributeName);
        return value;
    }
    @Override
    // 有些元素可以通过javaScript调用来强制点击某个元素
    public void jsClick(Position position) throws Exception {
        WebElement ele = getElement(position);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
        logs.info("JavaScript-click元素："+position.getPositionName()+"["+"By."+position.getType()+":"+position.getPath()+"] success！");
        Reporter.log("JavaScript-click元素："+position.getPositionName()+"["+"By."+position.getType()+":"+position.getPath()+"] success！");
    }

}
