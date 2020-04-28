package com.gmsj.dao;
import org.apache.ibatis.annotations.Param;

import com.gmsj.model.po.CardBrowsePo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 16:55
 */
public interface CardBrowseDao extends Mapper<CardBrowsePo> {

    Integer countByBizUserId(@Param("bizUserId")Integer bizUserId);

}