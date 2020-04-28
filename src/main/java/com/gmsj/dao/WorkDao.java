package com.gmsj.dao;

import com.gmsj.model.po.WorkPo;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 11:36
 */
public interface WorkDao extends Mapper<WorkPo> {
    /**
     * 查询
     */
    List<WorkPo> selectPageByMenuIdAndValid(@Param("menuId") int menuId, @Param("valid") int valid);

    /**
     * 莫负搜索招聘信息
     */
    List<WorkPo> selectFuzzyWork(String keyword);
}