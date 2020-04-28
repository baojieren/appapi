package com.gmsj.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baojieren
 * @date 2020/4/23 10:31
 */
public enum WorkExperienceEnum {
    BX(0, "不限"),
    YI_N(1, "1年以内"),
    SAN_N(2, "1-3年"),
    WU_N(3, "3-5年"),
    SHI_N(4, "5-10年");

    public int key;
    public String value;

    WorkExperienceEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<Map<String, Object>> toList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WorkExperienceEnum entity : WorkExperienceEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            list.add(map);
            map.put("key", entity.getKey());
            map.put("value", entity.getValue());
        }
        return list;
    }

    public static String getValue(int key) {
        for (WorkExperienceEnum entity : values()) {
            if (entity.getKey() == key) {
                return entity.getValue();
            }
        }
        return "";
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
