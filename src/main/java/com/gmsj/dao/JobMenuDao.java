package com.gmsj.dao;

import com.gmsj.model.po.JobMenuPo;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 11:00
 */
public interface JobMenuDao extends Mapper<JobMenuPo> {
    /**
     * 获取职位分类列表
     */
    List<JobMenuPo> selectAllByValid(@Param("valid") Integer valid);
}