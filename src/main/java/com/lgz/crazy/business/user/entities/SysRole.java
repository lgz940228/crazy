package com.lgz.crazy.business.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class SysRole implements Serializable{
    private Integer id;
    private String roleName;
    private String available;
    private String roleKey;
    private String roleSort;
    private String dataScope;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String remark;
}
