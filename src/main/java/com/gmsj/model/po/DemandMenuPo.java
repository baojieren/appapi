package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/22 12:13
 */

/**
 * 需求功能菜单表
 */
@Data
@Table(name = "demand_menu")
public class DemandMenuPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 父级id 一级菜单为0
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 菜单级别
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 菜单名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 排序优先级,值越小越高
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 0:无效 1:有效
     */
    @Column(name = "`valid`")
    private Integer valid;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}