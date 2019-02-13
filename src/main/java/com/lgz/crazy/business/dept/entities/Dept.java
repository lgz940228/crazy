package com.lgz.crazy.business.dept.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by lgz on 2019/2/13.
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String dbSource;
}
