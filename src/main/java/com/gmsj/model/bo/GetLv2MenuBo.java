package com.gmsj.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 10:22
 */
@Data
public class GetLv2MenuBo implements Serializable {

    public List<GetDemandMenuBo> lv2MenuList;

    public List<GetLv2MenuArticleBo> articleBoList;
}
