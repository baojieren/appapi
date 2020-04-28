package com.gmsj.model.bo;

import com.gmsj.model.po.SearchHistoryPo;
import com.gmsj.model.po.UserPo;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: 贵煤数据
 * @Author: zlh
 * @Date: 2020/4/23 18:11
 * @Description: 极速找人页面bean
 */
@Data
public class UserFindFastBo {


    /**
     * 用户列表
     */
    private List<UserPo> userPos;

}
