package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 11:00
 */

/**
 * 岗位列表
 */
@Data
@Table(name = "job_menu")
public class JobMenuPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 菜单名称, UI设计,技工
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 值越小,优先级越高
     */
    @Column(name = "sort")
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