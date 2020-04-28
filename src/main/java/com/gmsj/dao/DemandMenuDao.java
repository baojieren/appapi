package com.gmsj.dao;

import com.gmsj.model.po.DemandMenuPo;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 12:13
 */
public interface DemandMenuDao extends Mapper<DemandMenuPo> {
    List<DemandMenuPo> selectLv1DemandMenu();

    List<DemandMenuPo> findLv2MenuByLv1(@Param("pid") int pid);
}