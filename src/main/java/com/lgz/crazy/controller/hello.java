package com.lgz.crazy.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lgz on 2019/1/16.
 */
@Controller
public class hello {
    @RequestMapping("/h")
    @ResponseBody
    public String h(HttpSession session){
        System.out.println("session="+session.getId());
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

    @ResponseBody
    @RequestMapping("excel")
    public String excel(HttpServletResponse response){

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("报表");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("我是中国人");
        try {
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition","attachment;filename="+new String("报表".getBytes("utf-8"),"ISO-8859-1"));
            book.write(out);
            //response.setHeader("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
