package com.lgz.crazy.business.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Data
@NoArgsConstructor
public class UserShior {
    private String id;
    private String mobile;
    private String salt;
    private String passwd;
    private List<SysPermission> permission;
    private List<String> perm;
}
