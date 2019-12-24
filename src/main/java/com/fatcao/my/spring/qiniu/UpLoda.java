package com.fatcao.my.spring.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;


/**
 * 七牛文件上传
 * @Author: FatCao
 * @Date: 2019-12-24 14:10
 */
public class UpLoda {

    public static void main(String[] args) {

        // AccessKey 值
        String accessKey = (String) QiniuUtil.getString("qiniu.accessKey");
        // SecretKey 值
        String secretKey = (String) QiniuUtil.getString("qiniu.secretKey");
        // 储存空间名字
        String bucket = (String) QiniuUtil.getString("qiniu.bucket");
        // 上传图片路径
        String loaclFilePath = "E:\\fatcao_home\\testUpload.txt";
        // 在七牛空间中的文件名字
        String qiniuFileName = "feicao3.txt";

        System.out.println(accessKey);
        System.out.println(secretKey);
        System.out.println(bucket);

        // 我的存储空间是华南地区，所以选择 Zone.huanan()
        Configuration configuration = new Configuration(Zone.huanan());
        UploadManager uploadManager = new UploadManager(configuration);

        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);

        try {
            // 传参为 本地文件路径，七牛空间中文件名，和uploadToken
            Response response = uploadManager.put(loaclFilePath, qiniuFileName, uploadToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println("putRet.key: "+putRet.key);
            System.out.println("putRet.hash: "+putRet.hash);
        } catch (QiniuException e) {
            Response response = e.response;
            System.err.println(response.toString());
            try {
                System.err.println(response.bodyString());
            } catch (QiniuException ex) {
                ex.printStackTrace();
            }
        }

    }


}
