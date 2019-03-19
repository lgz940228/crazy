package com.lgz.crazy.common.service.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lgz.crazy.common.dao.CommonDao;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.entities.SysDictionary;
import com.lgz.crazy.common.service.CommonService;
import com.lgz.crazy.common.service.CommonUtil;
import com.lgz.crazy.common.utils.FtpUtil;
import com.lgz.crazy.common.utils.ObtainLatAndLngByBaiDuUtil;
import com.lgz.crazy.common.utils.collection.CollectionUtil;
import com.lgz.crazy.common.utils.num.NumUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lgz on 2019/3/15.
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger Log = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private DefaultKaptcha sZKaptcha ;
    @Autowired
    private CommonDao commonDao;

    @Override
    public Res<List<String>> handleFileUpload(HttpServletRequest request) {
        Res res = Res.getFailedResult();
        try{
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
                Integer integer = CommonUtil.checkIsImg(list);
                if(NumUtil.eq(-1,integer)){
                    res.setStatus(0);
                    res.setMsg("图片格式不支持");
                    return res;
                }else if (NumUtil.eq(0,integer)){
                    res.setStatus(0);
                    res.setMsg("请上传图片");
                    return res;
                }
                return FtpUtil.upload("/images",list);
            } else {
                res.setMsg("no file");
            }
        }catch (Exception e){
            Log.error("图片上传异常",e);
        }
        return res;
    }

    @Override
    public ResponseEntity<byte[]> getKaptchaImage(HttpSession session) {
        ResponseEntity<byte[]> responseEntity = null;
        ByteArrayOutputStream out = null;
        try {
            String capText = sZKaptcha.createText();
            Log.debug("******************生成验证码: {}******************",capText);
            //存贮验证码
            session.setAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY, capText);
            //String sessionId = CookieUtils.getCookieValue(request, Constant.MMCWEB_USER_MK);
            //RedisFactory.getClusterValueCommands("order_serial").set(RedisNameSpaceCode.MMC_IMAGE_CODE, Constants.KAPTCHA_SESSION_CONFIG_KEY+sessionId, capText,30, TimeUnit.MINUTES);
            //GenCookie.setHighleLevelCooikeByEnv(BaseGlobalConstant.COOKIEKEY_LOGINYZM, capText, 2*GenCookie.AGE_MINUTE,BaseGlobalConstant.PROPERTIESVALUE_ENV, request,response);
            // 创建图片
            BufferedImage bi = sZKaptcha.createImage(capText);
            out = new ByteArrayOutputStream();
            ImageIO.write(bi, "JPEG", out);
            HttpHeaders headers= new HttpHeaders();
            headers.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
            headers.setPragma("no-cache");
            headers.setExpires(0);
            headers.setContentType(MediaType.IMAGE_JPEG);
            responseEntity = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            Log.error("生成验证码异常", e);
        } finally {
            if(out != null) {
                IOUtils.closeQuietly(out); //关闭流
            }
        }
        return responseEntity;
    }

    @Override
    public Res<String[]> ObtainLatAndLng(String addr) {
        Res res = Res.getFailedResult();
        try {
            String[] coordinate = ObtainLatAndLngByBaiDuUtil.getCoordinate(addr);
            if(coordinate == null){
                res.setMsg("获取失败");
                return res;
            }
            return Res.getSuccessResult(coordinate,null);
        }catch (Exception e){
            res.setMsg("获取失败");
            return res;
        }
    }

    @Override
    public Res<List<SysDictionary>> querySysDictionary(String status,String dictType) {
        Res res = Res.getFailedResult();
        try {
            List<SysDictionary> sysDictList = commonDao.querySysDictionary(status, dictType);
            if(CollectionUtil.isNotNull(sysDictList)){
                return Res.getSuccessResult(sysDictList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
