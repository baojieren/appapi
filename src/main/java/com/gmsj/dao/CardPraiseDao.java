package com.gmsj.dao;
import org.apache.ibatis.annotations.Param;

import com.gmsj.model.po.CardPraisePo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 16:55
 */
public interface CardPraiseDao extends Mapper<CardPraisePo> {

    Integer countByBizUserId(@Param("bizUserId")Integer bizUserId);

    CardPraisePo selectByBizUserId(@Param("myId") int myId, @Param("bizUserId") int bizUserId);

}