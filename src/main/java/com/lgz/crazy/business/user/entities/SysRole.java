package com.lgz.crazy.business.user.entities;

import com.lgz.crazy.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SysRole extends BaseEntity{
    private Integer id;
    private String roleName;
    private String available;
    private String roleKey;
    private String roleSort;
    private String dataScope;
}
