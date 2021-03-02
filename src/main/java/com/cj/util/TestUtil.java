package com.cj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cj.data.Page;
import com.cj.data.ResponseEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class TestUtil {

    /**
     *
     * @param responseJson  这个变量是拿到响应字符串通过json转换成json对象
     * @param path 这个jpath指的是用户想要查询json对象的值的路径写法
     *        jpath写法举例：
     *               1） per_page
     *               2) data[1]/first_name, data是一个json数据， [1]表示索引
     *
     * @return
     */
    public static String getValueByJPath(String responseString, String path){
        ResponseEntity jsonToBean = JsonUtils.getJsonToBean(responseString, ResponseEntity.class);
        Map<String, String> data = (Map<String, String>) jsonToBean.getData();
        return data.get(path);
    }
    public static String getValueByJPath(JSONObject responseJson, String jpath) {

        Object obj = responseJson;

        for (String s : jpath.split("/")) {
            System.out.println(s);
            if (!s.isEmpty()) {
                if (!(s.contains("[") || s.contains("]"))) {
                    obj = ((JSONObject) obj).get(s);
                } else if (s.contains("[") || s.contains("]")) {
                    // obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("\\]", "")));
                    System.out.println(s);
                    Object o = ((JSONObject) obj).get(s.split("\\["));
                    JSONArray jsonArray = (JSONArray) o;
                    obj = jsonArray.get(Integer.parseInt(s.split("\\[")[1].replaceAll("\\]", "")));
                }
            }
        }

        return obj.toString();
    }

    // 通过Json转换成Page
    public static Page getPageValueByJson(CloseableHttpResponse closeableHttpResponse) throws IOException {
//        String str = EntityUtils.toString(closeableHttpResponse.getEntity());
//        ResponseEntity responseEntity = JsonUtils.getJsonToBean(str, ResponseEntity.class);
        ResponseEntity responseEntity = getResponseEntityValueByJson(closeableHttpResponse);
        String datas = ((JSONObject) responseEntity.getData()).toJSONString();
        Page page = JsonUtils.getJsonToBean(datas, Page.class);
        return page;
    }

    // 通过Json转换成ResponseEntity
    public static ResponseEntity getResponseEntityValueByJson(CloseableHttpResponse closeableHttpResponse) throws IOException {
        String str = EntityUtils.toString(closeableHttpResponse.getEntity());
        ResponseEntity responseEntity = JsonUtils.getJsonToBean(str, ResponseEntity.class);
        return responseEntity;
    }
}
