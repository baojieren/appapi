package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/24 14:24
 */

/**
 * 名片夹
 */
@Data
@Table(name = "card_folder")
public class CardFolderPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 联系人id
     */
    @Column(name = "biz_user_id")
    private Integer bizUserId;

    /**
     * 0:未查看,1:已查看
     */
    @Column(name = "`valid`")
    private Integer valid;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}