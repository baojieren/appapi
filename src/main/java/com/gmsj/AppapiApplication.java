package com.gmsj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.gmsj.dao"})
@EnableFeignClients
public class AppapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppapiApplication.class, args);
    }

}
