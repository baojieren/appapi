package com.gmsj.model.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author baojieren
 * @date 2020/4/22 9:47
 */
@Data
public class GetDemandMenuBo implements Serializable {
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;
}