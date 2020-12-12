package com.cj.util;

import java.io.*;

public class TokenUtil {

    File file = new File("src/main/resources/token.txt");

    public boolean save(String token) throws IOException {
        // 判断是否有文件
        if(!file.exists()){
            // 没有就创建
            file.createNewFile();
        }
        // 创建输出流
        FileOutputStream fos = new FileOutputStream(file);
        // 把token写在文件中
        byte[] data = token.getBytes();
        fos.write(data);
        // 显示文件的大小
        long size = file.length();
        // 如果有数据，则true，如果没有数据，则false
        if(size > 0){
            return true;
        }
        return false;
    }

    public String getToken() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        String token = br.readLine();

        return token;
    }

}
