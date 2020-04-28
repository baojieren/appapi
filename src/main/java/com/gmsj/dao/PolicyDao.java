package com.gmsj.dao;

import org.apache.ibatis.annotations.Param;

import com.gmsj.model.po.PolicyPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 14:41
 */
public interface PolicyDao extends Mapper<PolicyPo> {

    List<PolicyPo> selectPageByPolicyMenuIdAndValid(@Param("policyMenuId") Integer policyMenuId);

}