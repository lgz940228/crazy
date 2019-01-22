package com.lgz.crazy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by lgz on 2019/1/16.
 */
@Controller
public class hello {
    @RequestMapping("/h")
    @ResponseBody
    public String h(){
        return "hello the world!";
    }
    @RequestMapping("s")
    public String success(){
        return "success";
    }

    @RequestMapping("index")
    public String index(Map<String,String> map){
        map.put("hello","你好");
        return "index";
    }
    @RequestMapping("index1")
    public ModelAndView index1(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("hello","你好");
        return mv;
    }
}
