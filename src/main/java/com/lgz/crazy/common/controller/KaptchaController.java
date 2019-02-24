package com.lgz.crazy.common.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by lgz on 2019/2/22.
 */
@Controller
@RequestMapping("api/common")
public class KaptchaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaptchaController.class);

    @Autowired
    private DefaultKaptcha sZKaptcha ;

    /**
     * Description: 生成验证码
     * @return
     */
    @RequestMapping(value = "/getYzmImage", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getKaptchaImage(HttpSession session) {

        ResponseEntity<byte[]> responseEntity = null;
        ByteArrayOutputStream out = null;
        try {
            String capText = sZKaptcha.createText();
            LOGGER.debug("******************生成验证码: {}******************",capText);
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
            LOGGER.error("生成验证码异常", e);
        } finally {
            if(out != null) {
                IOUtils.closeQuietly(out); //关闭流
            }
        }
        return responseEntity;
    }
}
