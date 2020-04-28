package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 11:45
 */

/**
 * 公司信息表
 */
@Data
@Table(name = "company")
public class CompanyPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 公司名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

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