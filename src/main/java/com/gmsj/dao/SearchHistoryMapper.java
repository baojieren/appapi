package com.gmsj.dao;


import com.gmsj.model.po.SearchHistoryPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 09:44
 * @description:
 */
public interface SearchHistoryMapper extends Mapper<SearchHistoryPo> {

    List<SearchHistoryPo> findHistory(@Param("userId") Integer userId, @Param("menuId") Integer menuId);

    Integer insertHistory(@Param("userId") Integer userId, @Param("menuId") Integer menuId, @Param("content") String content);
}
