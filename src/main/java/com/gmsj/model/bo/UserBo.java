package com.gmsj.model.bo;

import lombok.Data;

/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 09:40
 * @description:
 */
@Data
public class UserBo {

    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String logo;

    /**
     * 微信openId
     */
    private String openId;


    /**
     * 职务
     */
    private String job;

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 邮箱
     */
    private String email;


}
