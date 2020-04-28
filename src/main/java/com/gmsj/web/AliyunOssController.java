package com.gmsj.web;

import com.gmsj.base.YmlConfig;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.util.AliyunOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author baojieren
 * @date 2020/4/23 15:35
 */
@Slf4j
@RestController
@RequestMapping("/oss")
public class AliyunOssController {
    @Resource
    YmlConfig ymlConfig;

    @PostMapping("upload")
    public BaseOutDTO uploadImg(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String fn = AliyunOssUtil.genFileName(file);
        AliyunOssUtil ossUtil = new AliyunOssUtil();
        String etag = ossUtil.upload2Oss(
                ymlConfig.getAliyunOssAccessKeyId(),
                ymlConfig.getAliyunOssAccessKeySecret(),
                ymlConfig.getAliyunOssBucketName(),
                ymlConfig.getAliyunOssEndPoint(),
                fn,
                file.getInputStream());
        return new BaseOutDTO().setData(ymlConfig.getAliyunOssDomain() + fn);
    }
}
