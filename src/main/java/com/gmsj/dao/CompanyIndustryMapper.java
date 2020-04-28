package com.gmsj.dao;

import com.gmsj.model.po.CompanyIndustryPo;
import tk.mybatis.mapper.common.Mapper;

/**
*  @author author
*/
public interface CompanyIndustryMapper extends Mapper<CompanyIndustryPo> {

    Integer findIndustryIdByCompanyId(Integer compantyId);
}