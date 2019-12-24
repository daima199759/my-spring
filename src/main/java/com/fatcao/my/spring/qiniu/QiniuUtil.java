package com.fatcao.my.spring.qiniu;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 七牛工具类
 * @Author: FatCao
 * @Date: 2019-12-23 16:37
 */
public class QiniuUtil {

    /**
     * 自定义获取 yml 文件的配置
     * @param key
     * @return
     */
    private static Map<String, Map<String, Object>> properties = new HashMap<>();

    static {
        Yaml yaml = new Yaml();
        try (InputStream in = QiniuUtil.class.getClassLoader().getResourceAsStream("application.yml")) {
            properties = yaml.loadAs(in, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getString(String key) {
        String[] split = key.split("\\.");
        Map<String, Map<String, Object>> returnKey = new HashMap<>();
        for (int i = 0; i < split.length - 1; i++) {
            if (i == 0) {
                returnKey = (Map) properties.get(split[i]);
                continue;
            }
            if (returnKey == null) break;
            returnKey = (Map) returnKey.get(split[i]);
        }
        return returnKey == null ? null : returnKey.get(split[(split.length - 1)]);
    }
}
