package com.gmsj.dao;

import com.gmsj.model.bo.CardFolderCountBo;import com.gmsj.model.po.CardFolderPo;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 14:24
 */
public interface CardFolderDao extends Mapper<CardFolderPo> {
    Integer countByBizUserId(@Param("bizUserId") Integer bizUserId);

    CardFolderPo selectByBizUserId(@Param("myId") int myId, @Param("bizUserId") int bizUserId);

    List<CardFolderCountBo> cardFolderCount();

    List<CardFolderPo> selectByUserId(int userId);
}