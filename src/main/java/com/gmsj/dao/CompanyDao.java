package com.gmsj.dao;
import com.gmsj.model.bo.CompanyBo;
import org.apache.ibatis.annotations.Param;

import com.gmsj.model.po.CompanyPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * @author baojieren
 * @date 2020/4/23 11:45
 */
public interface CompanyDao extends Mapper<CompanyPo> {

    CompanyPo selectOneByName(@Param("name")String name);

    List<CompanyBo> matchCompany(String companyName);

}