package com.gmsj.web.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 10:04
 */
@Data
public class SaveCardInfoForm implements Serializable {

    public Integer id;

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
     * 公司id
     */
    public Integer companyId;

    /**
     * 公司名称
     */
    public String companyName;

    /**
     * 职务
     */
    public String job;

    /**
     * 邮箱
     */
    public String email;

    /**
     * 国家名称
     */
    public String countryName;

    /**
     * 国家编码
     */
    public String countryCode;

    /**
     * 省份名称
     */
    public String provinceName;

    /**
     * 省份编码
     */
    public String provinceCode;

    /**
     * 市区名称
     */
    public String cityName;

    /**
     * 市区编码
     */
    public String cityCode;

    /**
     * 详细地址
     */
    public String addr;

    /**
     * 个人描述
     */
    public String remark;

    /**
     * 公司行业id
     */
    public List<Integer> industryList;

}
