package com.lgz.crazy.business.user.entities;

import com.lgz.crazy.business.admin.entities.Menu;
import com.lgz.crazy.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SysRole extends BaseEntity{
    private Integer id;
    private String roleName;
    private String available;
    private String roleKey;
    private String roleSort;
    private String dataScope;
    private List<Menu> menus;
}
