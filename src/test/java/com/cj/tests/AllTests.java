package com.cj.tests;

import com.alibaba.fastjson.JSON;
import com.cj.base.TestBase;
import com.cj.data.Users;
import com.cj.restClient.RestClient;
import com.cj.util.TestUtil;
import com.cj.util.TokenUtil;
import com.cj.util.VolunteerTeam_WebElement;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class AllTests {

    private WebDriver driver;

    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    TokenUtil saveToken;

    final static Logger log = Logger.getLogger(PostApiTest.class);

    @BeforeClass
    public void setUp(){

        testBase = new TestBase();
        host = testBase.prop.getProperty("SASS_HOST");
        url = host + "/doLoginFromVue";

        driver = new ChromeDriver();
    }
    // 登录是否成功
    @Test
    public void loginTest() throws Exception{
        // driver.get("http://192.168.1.99:9528/#/volunteerTeam/volunteerTeamAdd");
        driver.get(VolunteerTeam_WebElement.LOGIN_URL);

        // driver.findElement(By.cssSelector("#app > div > div > div:nth-child(2) > div > form > div:nth-child(2) > div > div > input")).sendKeys("test");
        // driver.findElement(By.cssSelector("#app > div > div > div:nth-child(2) > div > form > div:nth-child(3) > div > div > input")).sendKeys("test");
        driver.findElement(By.cssSelector(VolunteerTeam_WebElement.LOGIN_NAME_INPUT)).sendKeys("cj");
        driver.findElement(By.cssSelector(VolunteerTeam_WebElement.LOGIN_PWD_INPUT)).sendKeys("123");

        Thread.sleep(3000);

        // driver.findElement(By.cssSelector("#app > div > div > div:nth-child(2) > div > form > button")).click();
        driver.findElement(By.cssSelector(VolunteerTeam_WebElement.LOGIN_BUTTON)).click();

        Thread.sleep(5000);

        String dashboard = driver.getCurrentUrl();

        System.out.println(dashboard);

        // assert dashboard.equals("http://192.168.1.99:9528/#/dashboard");
        assert dashboard.contains(VolunteerTeam_WebElement.DASHBOARD);
    }
    @Test
    public void getAPITest() throws IOException {
        log.info("开始执行用例...");
        restClient = new RestClient();

        //准备请求头信息
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到

        //对象转换成Json字符串
        Users user = new Users("cimsadmin", "x65Z7qLM", "MA1H8GLY-6", "00006");
        String userJsonString = JSON.toJSONString(user);
        //System.out.println(userJsonString);

        closeableHttpResponse = restClient.post(url, userJsonString, headermap);

        // 断言状态是否是200
        log.info("测试响应状态码是否是200");
        int statusCode = restClient.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode, TestBase.RESPNSE_STATUS_CODE_200, "respinse status code is not 200");

        // Json内容解析
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        // System.out.println(responseString);
        String realName = TestUtil.getValueByJPath(responseString, "realName");
        String userName = TestUtil.getValueByJPath(responseString, "userName");
        Assert.assertEquals(realName,
                "cims管理员",
                "name is not cims管理员");

        Assert.assertEquals(userName,
                "cimsadmin",
                "job is not cimsadmin");
        // 断言没有问题，就可以把
        String token = TestUtil.getValueByJPath(responseString, "token");
        // System.out.println(token);
        // 保存token
        saveToken = new TokenUtil();
        Assert.assertEquals(saveToken.save(token), true, "token保存失败");
    }
}

