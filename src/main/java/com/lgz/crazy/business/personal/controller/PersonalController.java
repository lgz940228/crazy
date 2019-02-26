package com.lgz.crazy.business.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lgz on 2019/2/26.
 */
@Controller
@RequestMapping("api/personal")
public class PersonalController {

    @RequestMapping("toPersonal")
    public ModelAndView toPersonal(){
        ModelAndView mv = new ModelAndView("person");
        return mv;
    }

}
