package com.fatcao.my.spring.asm;

/**
 * @Author: FatCao
 * @Date: 2019-12-23 15:10
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] bytes) {
        // ClassLoader 是一个抽象类，而 classLoader.defineClass 方法是protected 的
        // 所以我们要定义一个子类把他暴露出来
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
