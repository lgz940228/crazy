package com.lgz.crazy.business.user.entities;

import com.lgz.crazy.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lgz on 2019/3/20.
 */
@Data
@NoArgsConstructor
public class UserVo extends BaseEntity{
    private Long id;
    private String loginName;
    private String passwd;
    private String userName;
    private String sex;
    private String tel;
    private String roleId;
    private String nick;
    private String salt;
    private String resetPwdTime;
    private String mobile;
    private String email;
    private Integer status;
    private String city;
    private String cancelTime;
    private String cancelReason;
    private String icon;
    private String personalizedSignature;
    private String birthday;
    private String img;
    private String rePwd;
    private List<SysRole> roles;

}
