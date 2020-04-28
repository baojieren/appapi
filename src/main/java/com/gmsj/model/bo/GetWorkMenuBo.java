package com.gmsj.model.bo;

import com.gmsj.model.po.JobMenuPo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 10:22
 */
@Data
public class GetWorkMenuBo implements Serializable {

    public List<JobMenuPo> workMenuList;

    public List<GetListWorkBo> workBoList;
}
