package com.gmsj.model.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserCardInfoBo implements Serializable {

    public Integer userId;

    /**
     * 用户名
     */
    public String userName;

    /**
     * 用户手机号
     */
    public String phone;

    /**
     * 头像
     */
    public String logo;

    /**
     * 公司名称
     */
    public String companyName;

    /**
     * 职务
     */
    public String job;

    /**
     * 详细地址
     */
    public String addr;

    /**
     * 邮箱
     */
    public String email;

    /**
     * 名片被保存数
     */
    public int cardSaveSum;

    /**
     * 名片被点赞数
     */
    public int praiseSum;

    /**
     * 名片被浏览数
     */
    public int browseSum;

}