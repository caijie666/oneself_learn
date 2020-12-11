package com.cj.tests;

import com.alibaba.fastjson.JSON;
import com.cj.base.TestBase;
import com.cj.data.Users;
import com.cj.restClient.RestClient;
import com.cj.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class PostApiTest {

    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    final static Logger log = Logger.getLogger(PostApiTest.class);
    final static int RESPNSE_STATUS_CODE_200 = 200;

    @BeforeClass
    public void setUp(){

        testBase = new TestBase();
        host = testBase.prop.getProperty("HOST");
        url = host + "/doLoginFromVue";

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
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "respinse status code is not 200");

        // Json内容解析
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        // System.out.println(responseString);
        String realName = TestUtil.getLoginValueByJPath(responseString, "realName");
        String userName = TestUtil.getLoginValueByJPath(responseString, "userName");
        Assert.assertEquals(realName, "cims管理员","name is not cims管理员");
        Assert.assertEquals(userName, "cimsadmin","job is not cimsadmin");


    }
}









