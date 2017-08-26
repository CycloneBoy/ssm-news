package com.nowcoder.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.nowcoder.controller.NewsController;
import com.nowcoder.service.AliyunService;
import com.nowcoder.util.AliyunOSSClientUtil;
import com.nowcoder.util.OSSClientConstants;
import com.nowcoder.util.ToutiaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
@Service
public class AliyunServiceImpl implements AliyunService{
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    // endpoint以杭州为例，其它region请按实际情况填写
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
    private String accessKeyId = "LTAIernIUzzwJlN8";
    private String accessKeySecret = "p854k9IPzzbA8Sc6npvytxchPsB7xC";

    private  String bucketName = "ssm-news";
    private  String key = "";
    private  String uploadFile = "<uploadFile>";
    @Override
    public String saveImage(MultipartFile file) throws IOException {

        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if(dotPos < 0){
            return  null;
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if(!ToutiaoUtil.isFileAllowed(fileExt)){
            return null;
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "."+fileExt;

        try {
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 设置断点续传请求
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,fileName);

            // 上传文件流
            InputStream inputStream = file.getInputStream();
            PutObjectResult result = ossClient.putObject(bucketName, fileName, inputStream);

            logger.info(result.toString());
            // 关闭client
            ossClient.shutdown();

        } catch (Throwable throwable) {
            logger.error("阿里云上传文件失败：" + throwable.getMessage());
            throwable.printStackTrace();
        }

        return  ToutiaoUtil.TOUTIAO_ALIYUN_DOMAIN+fileName;

    }


}
