package com.gmsj.model.bo;

import lombok.Data;

/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 14:17
 * @description:
 */
@Data
public class UserCountCompareBo implements Comparable<UserCountCompareBo> {

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

    /**
     * 名片被保存数
     */
    private Integer countFolder;

    /**
     * 名片被保存数
     */
    private Integer countPraise;

    /**
     * 名片被保存数
     */
    private Integer countBrowse;

    @Override
    public int compareTo(UserCountCompareBo userCountCompareBo) {
        return userCountCompareBo.countFolder - this.countFolder;
    }
}
