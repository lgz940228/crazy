package com.lgz.crazy.common.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lgz on 2019/3/19.
 */
@Data
@NoArgsConstructor
public class SysDictionary implements Serializable{
    private String id;
    private String dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String cssClass;
    private String listClass;
    private String isDefault;
    private String status;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
}
