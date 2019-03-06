package com.lgz.crazy.business.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lgz on 2019/3/6.
 */
@Data
@NoArgsConstructor
public class SysPermission {
    private String id;
    private String url;
    private String type;
}
