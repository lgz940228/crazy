package com.lgz.crazy.business.admin.entities;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private String id;
    private String icon;
    private String menuName;
    private List<Menu> children;
    private String url;
}
