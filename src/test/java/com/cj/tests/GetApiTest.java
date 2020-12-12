package com.cj.tests;

import com.alibaba.fastjson.JSONObject;
import com.cj.base.TestBase;
import com.cj.restClient.RestClient;
import com.cj.util.TokenUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {

    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    TokenUtil tokenUtil;

    final static Logger log = Logger.getLogger(GetApiTest.class);

    @BeforeClass
    public void setUp(){

        testBase = new TestBase();
        host = prop.getProperty("CIMS_HOST");
        url = host + "/cims/peaLab/findAll?EQ_peaLabId=&EQ_labName=&EQ_doorNum=&EQ_lineNo=&EQ_adClientId=&pageIndex=0&pageSize=5&sortField=&sortOrder=";

    }

    @Test
    public void getAPITest() throws IOException {
        log.info("获取所有实验室信息列表————开始执行用例...");

        restClient = new RestClient();
        tokenUtil = new TokenUtil();

        //准备请求头信息
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        // 获取token
        String token = tokenUtil.getToken();
        headermap.put("X-Token", token);
        // 通过get方法，发送请求，获取到httpresponse对象
        closeableHttpResponse = restClient.get(url, headermap);

        // 断言状态是否是200
        log.info("测试响应，状态码是否是200");
        // 通过getStatueCode方法获取httpresponse对象的响应的信息
        int statusCode = restClient.getStatusCode(closeableHttpResponse);
        // 断言是否是200
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "response status code is not 200");

        // 客户端通过httprespose对象的数据转换成Json
        JSONObject responseJson = restClient.getResponseJson(closeableHttpResponse);
        System.out.println(responseJson.toString());
    }

}
