package com.lgz.crazy.business.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lgz on 2019/2/20.
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
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
    private String createTime;
    private String email;
    private Integer status;
    private String city;
    private String cancelTime;
    private String cancelReason;
    private String icon;
    private String personalizedSignature;
    private String birthday;
    private List<String> img;
}
