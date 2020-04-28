package com.gmsj.model.bo;

import com.gmsj.model.po.JobMenuPo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author baojieren
 * @date 2020/4/23 17:56
 */
@Data
public class PublishWorkLv2MenuListBo implements Serializable {
    public List<JobMenuPo> menuList;
    public List<Map<String, Object>> educationList;
    public List<Map<String, Object>> workExperienceList;
}
