package com.cj.util;

import com.cj.data.ResponseEntity;

import java.util.Map;

public class TestUtil {

    /**
     *
     * @param responseJson  这个变量是拿到响应字符串通过json转换成json对象
     * @param jpath 这个jpath指的是用户想要查询json对象的值的路径写法
     *        jpath写法举例：
     *               1） per_page
     *               2) data[1]/first_name, data是一个json数据， [1]表示索引
     *
     * @return
     */
    public static String getLoginValueByJPath(String responseString, String jpath){
        ResponseEntity jsonToBean = JsonUtils.getJsonToBean(responseString, ResponseEntity.class);
        Map<String, String> data = (Map<String, String>) jsonToBean.getData();
        return data.get(jpath);
    }
}
