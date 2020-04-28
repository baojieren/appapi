package com.gmsj.model.bo;

import lombok.Data;


/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 09:01
 * @description:
 */
@Data
public class CardFolderCountBo implements Comparable<CardFolderCountBo> {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 联系人id
     */
    private Integer bizUserId;

    /**
     * 联系人总和
     */
    private Integer countFolder;


    @Override
    public int compareTo(CardFolderCountBo cardFolderCountBo) {
        return cardFolderCountBo.countFolder - this.countFolder;
    }
}
