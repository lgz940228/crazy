package com.lgz.crazy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
