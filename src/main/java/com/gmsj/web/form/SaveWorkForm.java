package com.gmsj.web.form;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 11:36
 */

/**
 * 招聘信息
 */
@Data
public class SaveWorkForm implements Serializable {

    /**
     * 发布者id
     */
    private Integer userId;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 岗位名称
     */
    private String workName;

    private String salary;

    /**
     * 工作地点
     */
    private String workAddr;

    /**
     * 0:不限 1:1年以下 2:1-3年 3:3-5年 4:5-10年
     */
    private Integer experience;

    /**
     * 0:不限,1:初中 2:高中 3:大专 4:本科
     */
    private Integer education;

    /**
     * 工作描述
     */
    private String workDesc;

    /**
     * 公司描述
     */
    private String companyDesc;

    /**
     * 福利
     */
    private String welfare;

    /**
     * 图片地址,多个用逗号隔开
     */
    private List<String> images;

    /**
     * 工作分类id
     */
    private Integer jobMenuId;

}