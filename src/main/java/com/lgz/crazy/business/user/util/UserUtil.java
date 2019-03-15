package com.lgz.crazy.business.user.util;

import com.lgz.crazy.common.utils.CheckUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lgz on 2019/3/15.
 */
public class UserUtil {

    /**
     * 判断用户类型
     * @param userName
     * @return 1 手机用户 2 邮箱用户 3 用户名 0 用户名不合法 -1 用户名为空
     */
    public static Integer getUserType(String userName){
        if(StringUtils.isBlank(userName))return -1;
        if(CheckUtil.isMobile(userName.trim())){
            return 1;
        }
        if(CheckUtil.isEmail(userName.trim())){
            return 2;
        }
        return 0;
    }
}
