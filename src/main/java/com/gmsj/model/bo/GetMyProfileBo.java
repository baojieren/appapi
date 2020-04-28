package com.gmsj.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 11:46
 */
@Data
public class GetMyProfileBo implements Serializable {

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
     * 邮箱
     */
    public String email;

    /**
     * 个人描述
     */
    public String remark;

    /**
     * 我公司所在行业
     */
    public List<Integer> myIndustryIdList;

    /**
     * 所有行业
     */
    public List<IndustryBo> industryList;

}
