package com.lgz.crazy.common.service;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

/**
 * Created by lgz on 2019/3/15.
 */
public class CommonUtil {

    /**
     * 判断是否是图片
     * @param list
     * @return -1 图片格式不支持 0 不是图片 1 是图片
     * @throws IOException
     */
    public static Integer checkIsImg(List<MultipartFile> list) throws IOException{
        for (MultipartFile file : list) {
            String originalFilename = file.getOriginalFilename();
            if (!(originalFilename.endsWith(".jpg")
                    || originalFilename.endsWith(".png")
                    || originalFilename.endsWith(".jpeg")
                    || originalFilename.endsWith(".bmp")
                    || originalFilename.endsWith(".gif")
            )) {
                return -1;
            }
            if (ImageIO.read(file.getInputStream()) == null) {
                return 0;
            }
        }
        return 1;
    }
}
