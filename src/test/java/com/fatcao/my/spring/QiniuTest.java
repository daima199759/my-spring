package com.fatcao.my.spring;

import com.qiniu.util.StringUtils;
import org.junit.Test;

/**
 * @Author: FatCao
 * @Date: 2019-12-24 14:31
 */
public class QiniuTest {
    @Test
    public void testOne(){
        byte[] bytes = StringUtils.utf8Bytes("DsPufr8klqiQuk6tjYLANZvQ_c40iZIZu_EE9Sgr");
        System.out.println(bytes.toString());
    }
}
