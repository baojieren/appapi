package com.gmsj.dao;

import com.gmsj.model.bo.SearchUserBo;
import com.gmsj.model.bo.UserBo;
import com.gmsj.model.bo.UserCountCompareBo;
import com.gmsj.model.po.UserPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/21 8:57
 */
public interface UserDao extends Mapper<UserPo> {
    List<UserCountCompareBo> searchUserBo(String userName);

    UserPo findUserById(Integer userId);


}