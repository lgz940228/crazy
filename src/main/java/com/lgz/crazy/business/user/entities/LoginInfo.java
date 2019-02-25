package com.lgz.crazy.business.user.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class LoginInfo implements Serializable {
    private Long id;
    private String userName;
    private String sex;
    private String tel;
    private String roleId;
    private String nick;
    private String resetPwdTime;
    private String mobile;
    private String createTime;
    private String email;
    private String city;
    private String cancelTime;
    private String cancelReason;
    private String icon;
    private String personalizedSignature;
    private String birthday;
    private List<String> img;
    private Integer loginType;//登录类型 1 手机号 2 邮箱
}
