package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 11:36
 */

/**
 * 招聘信息
 */
@Data
@Table(name = "`work`")
public class WorkPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 发布者id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 岗位名称
     */
    @Column(name = "work_name")
    private String workName;

    @Column(name = "salary")
    private String salary;

    /**
     * 工作地点
     */
    @Column(name = "work_addr")
    private String workAddr;

    /**
     * 0:不限 1:1年以下 2:1-3年 3:3-5年 4:5-10年
     */
    @Column(name = "experience")
    private Integer experience;

    /**
     * 0:不限,1:初中 2:高中 3:大专 4:本科
     */
    @Column(name = "education")
    private Integer education;

    /**
     * 工作描述
     */
    @Column(name = "work_desc")
    private String workDesc;

    /**
     * 公司描述
     */
    @Column(name = "company_desc")
    private String companyDesc;

    /**
     * 福利
     */
    @Column(name = "welfare")
    private String welfare;

    /**
     * 图片地址,多个用逗号隔开
     */
    @Column(name = "images")
    private String images;

    /**
     * 工作分类id
     */
    @Column(name = "job_menu_id")
    private Integer jobMenuId;

    /**
     * 浏览量
     */
    @Column(name = "browse_sum")
    private Integer browseSum;

    /**
     * 收藏量
     */
    @Column(name = "collect_sum")
    private Integer collectSum;

    /**
     * 0:无效 1:有效
     */
    @Column(name = "`valid`")
    private Integer valid;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}