package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/24 14:47
 */

/**
 * 政策资讯菜单
 */
@Data
@Table(name = "policy_menu")
public class PolicyMenuPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 菜单名称, 安监局 国土局
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