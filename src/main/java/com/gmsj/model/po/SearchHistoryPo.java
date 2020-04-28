package com.gmsj.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*  @author zlh
*/
@Data
@Table(name = "search_history")
public class SearchHistoryPo implements Serializable {

    private static final long serialVersionUID = 1587692268833L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Integer id;

    /**
    * 
    * isNullAble:0
    */
    @Column(name = "user_id")
    private Integer userId;

    /**
    * 菜单id
    * isNullAble:1
    */
    @Column(name = "demand_menu_id")
    private Integer demandMenuId;

    /**
    * 
    * isNullAble:0
    */
    private String content;

    /**
    * 
    * isNullAble:1,defaultVal:CURRENT_TIMESTAMP
    */
    private java.time.LocalDateTime createTime;

    /**
    * 
    * isNullAble:1,defaultVal:CURRENT_TIMESTAMP
    */
    private java.time.LocalDateTime updateTime;

}
