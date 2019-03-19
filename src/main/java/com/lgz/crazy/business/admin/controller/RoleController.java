package com.lgz.crazy.business.admin.controller;

import com.lgz.crazy.business.admin.service.AdminService;
import com.lgz.crazy.business.admin.service.SysRoleService;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.common.constant.Constant;
import com.lgz.crazy.common.core.controller.BaseController;
import com.lgz.crazy.common.core.page.TableDataInfo;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.entities.SysDictionary;
import com.lgz.crazy.common.service.CommonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lgz on 2019/3/18.
 */
@Controller
@RequestMapping("/api/role/")
public class RoleController extends BaseController{

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommonService commonService;
    @Autowired
    private SysRoleService sysRoleService;
    @RequestMapping("toRole")
    public ModelAndView toRole(){
        ModelAndView mv = new ModelAndView("ruoyi/system/role/role");
        Res<List<SysDictionary>> sysDictionaryRes = commonService.querySysDictionary("1", Constant.SYS_NORMAL_DISABLE);
        if(Res.isSuc(sysDictionaryRes)){
            mv.addObject("sysNormalDisable",sysDictionaryRes.getData());
        }
        Subject subject = SecurityUtils.getSubject();
        boolean editFlag = subject.isPermitted("system:role:edit");
        boolean removeFlag = subject.isPermitted("system:role:remove");
        mv.addObject("editFlag",editFlag);
        mv.addObject("removeFlag",removeFlag);
        return mv;
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRole role)
    {
        try {
            startPage();
            List<SysRole> list = sysRoleService.selectRoleList(role);
            return getDataTable(list);
        }catch (Exception e){
            e.printStackTrace();
        }
      return null;
    }
    /*@RequestMapping("toRole")
    @ResponseBody
    public String toTest(){
        return "success";
    }*/
}
