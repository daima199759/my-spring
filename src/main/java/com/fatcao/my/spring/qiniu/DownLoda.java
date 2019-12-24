package com.fatcao.my.spring.qiniu;

import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;

/**
 * 七牛的文件下载
 *
 * @Author: FatCao
 * @Date: 2019-12-24 14:06
 */
public class DownLoda {

    // AccessKey 值
    String accessKey = (String) QiniuUtil.getString("qiniu.accessKey");
    // SecretKey 值
    String secretKey = (String) QiniuUtil.getString("qiniu.secretKey");
    // 密钥配置
    Auth auth = Auth.create(accessKey, secretKey);

    public static void main(String[] args) {

        // 格式：http://域名/空间下的文件名
        String targetUrl = "http://q30675cmy.bkt.clouddn.com/feicao2.txt";
        // 存储路径
        String filePath = "E:\\fatcao_home\\";

        download(targetUrl, filePath);
    }

    /**
     * 获取下载文件的路径，即 DownloadUrl
     *
     * @param targetUrl
     * @return
     */
    public String getDownloadUrl(String targetUrl) {
        return auth.privateDownloadUrl(targetUrl);
    }

    /**
     * 通过发送 Http Get 请求获取文件资源
     *
     * @param url
     * @param filePath
     */
    public static void download(String url, String filePath) {
        OkHttpClient client = new OkHttpClient();
        System.out.println("url: " + url);
        Request req = new Request.Builder().url(url).build();
        Response resp = null;
        try {
            resp = client.newCall(req).execute();
            System.out.println(resp.isSuccessful());
            if (resp.isSuccessful()) {
                ResponseBody body = resp.body();
                InputStream is = body.byteStream();
                byte[] data = readInputStream(is);
                File file = new File(filePath + "okok.txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取字节输入流内容
     *
     * @param is
     * @return
     */
    private static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024 * 2];
        int len = 0;
        try {
            while ((len = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
