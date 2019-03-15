package com.lgz.crazy.common.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by lgz on 2019/3/4.
 */
@Controller
@RequestMapping("common")
public class CommonController {

    private static final Logger Log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonServiceImpl;

    @Autowired
    private DefaultKaptcha sZKaptcha ;

    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Res<List<String>> handleFileUpload(HttpServletRequest request) {

        Res res = Res.getFailedResult();
        try {
            return commonServiceImpl.handleFileUpload(request);
        } catch (Exception e) {
            Log.error("上传文件失败", e);
            res.setMsg("upload failed...");
        }
        return res;
    }

    @RequestMapping("/toFtp")
    public ModelAndView ftp(){
        ModelAndView mv = new ModelAndView("ftp");
        mv.addObject("url","39.105.206.67/uploads/images/3c75d6e5-65e8-49b1-9d69-5132d0c6371d-1551691140529.png");
        return mv;
    }

    /**
     * Description: 生成验证码
     * @return
     */
    @RequestMapping(value = "/getYzmImage", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getKaptchaImage(HttpSession session) {
        return commonServiceImpl.getKaptchaImage(session);
    }

    @RequestMapping("/obtainLatAndLng")
    @ResponseBody
    public Res<String[]> ObtainLatAndLng(String addr){
        return commonServiceImpl.ObtainLatAndLng(addr);
    }
}
