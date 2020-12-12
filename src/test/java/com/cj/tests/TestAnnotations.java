package com.cj.tests;

import org.testng.annotations.*;

public class TestAnnotations {

    @BeforeClass
    public void setup(){
        System.out.println("beforeClass 开始");
    }

    @AfterClass
    public void setAfter(){
        System.out.println("afterClass 开始");
    }

    @BeforeTest
    public void testBefore(){
        System.out.println("beforeTest 开始");
    }

    @AfterTest
    public void testAfter(){
        System.out.println("afterTest 开始");
    }

    @Test
    public void test01(){
        System.out.println("test01 执行");
    }

    @Test
    public void test02(){
        System.out.println("test02 执行");
    }
}
