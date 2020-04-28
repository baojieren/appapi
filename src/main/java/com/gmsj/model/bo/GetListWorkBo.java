package com.gmsj.model.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 招聘信息
 *
 * @author baojieren
 * @date 2020/4/23 10:19
 */
@Data
public class GetListWorkBo implements Serializable {
    private Integer id;

    /**
     * 发布者id
     */
    private Integer userId;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 图片
     */
    private String image;

    /**
     * 岗位名称
     */
    private String workName;

    /**
     * 工资
     */
    private String salary;

    /**
     * 工作地点
     */
    private String workAddr;

    /**
     * 0:不限 1:1年以下 2:1-3年 3:3-5年 4:5-10年
     */
    private String experience;

    /**
     * 0:不限,1:初中 2:高中 3:大专 4:本科
     */
    private String education;

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
     * 浏览量
     */
    private Integer browseSum;

    /**
     * 收藏量
     */
    private Integer collectSum;

    private String createTime;
}