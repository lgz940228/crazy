package com.lgz.crazy.common.controller;

import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.FtpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by lgz on 2019/3/4.
 */
@Controller
@RequestMapping("api/common")
public class CommonController {

    private static final Logger Log = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Res<List<String>> handleFileUpload(HttpServletRequest request) {

        Res res = Res.getFailedResult();
        try {
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
                String type = request.getParameter("type");
                Collection<List<MultipartFile>> value = req.getMultiFileMap().values();
                List<MultipartFile> list = new LinkedList<MultipartFile>();
                for (List<MultipartFile> temp : value) {
                    if (temp != null && temp.size() > 0) {
                        list.addAll(temp);
                    }
                }
                for (MultipartFile file : list) {
                    String originalFilename = file.getOriginalFilename();
                    if (!(originalFilename.endsWith(".jpg")
                            || originalFilename.endsWith(".png")
                            || originalFilename.endsWith(".jpeg")
                            || originalFilename.endsWith(".bmp")
                            || originalFilename.endsWith(".gif")
                    )) {
                        res.setStatus(0);
                        res.setMsg("图片格式不支持");
                        return res;
                    }
                    if (ImageIO.read(file.getInputStream()) == null) {
                        res.setStatus(0);
                        res.setMsg("请上传图片");
                        return res;
                    }
                }
               return FtpUtil.upload("/images",list);
            } else {
                res.setMsg("no file");
            }
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

}
