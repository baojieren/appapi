package com.gmsj.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author baojieren
 * @date 2020/4/22 9:54
 */
@Data
public class GetLv1MenuListBo implements Serializable {
    private Integer id;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 菜单名称
     */
    private String name;
}
