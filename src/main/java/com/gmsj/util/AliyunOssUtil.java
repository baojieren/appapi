package com.gmsj.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author baojieren
 * @date 2020/4/23 14:37
 */
public class AliyunOssUtil {
    /**
     * 上传图片
     */
    public String upload2Oss(
            String accessKey,
            String accessKeySecret,
            String bucketName,
            String endPoint,
            String fileName,
            InputStream inputStream) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                endPoint,
                accessKey,
                accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, inputStream);

        ossClient.shutdown();
        return putObjectResult.getETag();
    }

    /**
     * 生成文件名
     */
    public static String genFileName(MultipartFile file) {
        // 文件名
        String filename = file.getOriginalFilename();
        // 文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        return DigestUtils.md5DigestAsHex(filename.getBytes()) + suffix;
    }
}
