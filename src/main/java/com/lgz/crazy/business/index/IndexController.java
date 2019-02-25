package com.lgz.crazy.business.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/api/index")
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("main");
        return mv;
    }
}
