package com.lgz.crazy.business.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SysMenu implements Serializable{
    private Integer id;
    private String menuName;
    private String parentId;
    private String orderNum;
    private String url;
    //菜单类型（M菜单 B按钮）
    private String menuType;
    //是否是子节点 0 父节点 1 子节点
    private String isChild;
    //菜单状态（1显示 0隐藏）
    private String visible;
    //权限标识
    private String perms;
    private String icon;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
}
