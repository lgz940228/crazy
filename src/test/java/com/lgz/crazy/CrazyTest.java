package com.lgz.crazy;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by lgz on 2019/2/22.
 * <p>
 * 可以在测试期间很方便的类似编码一样进行自动注入到容器的功能
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyTest {

    @Autowired
    private DefaultKaptcha sZKaptcha;

    @org.junit.Test
    public void getKaptchaImage() {

        ResponseEntity<byte[]> responseEntity = null;
        ByteArrayOutputStream out = null;
        try {
            String capText = sZKaptcha.createText();
            //LOGGER.debug("******************生成验证码: {}******************", capText);
            //存贮验证码
            //session.setAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY, capText);
            //String sessionId = CookieUtils.getCookieValue(request, Constant.MMCWEB_USER_MK);
            //RedisFactory.getClusterValueCommands("order_serial").set(RedisNameSpaceCode.MMC_IMAGE_CODE, Constants.KAPTCHA_SESSION_CONFIG_KEY+sessionId, capText,30, TimeUnit.MINUTES);
            //GenCookie.setHighleLevelCooikeByEnv(BaseGlobalConstant.COOKIEKEY_LOGINYZM, capText, 2*GenCookie.AGE_MINUTE,BaseGlobalConstant.PROPERTIESVALUE_ENV, request,response);

            // 创建图片
            BufferedImage bi = sZKaptcha.createImage(capText);

            out = new ByteArrayOutputStream();
            ImageIO.write(bi, "JPEG", out);

            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
            headers.setPragma("no-cache");
            headers.setExpires(0);
            headers.setContentType(MediaType.IMAGE_JPEG);
            responseEntity = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            //LOGGER.error("生成验证码异常", e);
        } finally {
            if (out != null) {
                IOUtils.closeQuietly(out); //关闭流
            }
        }
        //return responseEntity;
        System.out.println(responseEntity);
    }
}
