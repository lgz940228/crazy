package com.lgz.crazy.baidu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lgz on 2019/3/12.
 */
public class ImgCensor {
    /**
     * https://ai.baidu.com/docs#/ImageCensoring-API/top
     * 百度AI文档（非官方整理）http://aixiaoshuai.mydoc.io/?t=247692
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static void main(String[] args) {
        // 图像组合APIurl
        String imgCensorUrl = "https://aip.baidubce.com/api/v1/solution/direct/img_censor";
        String filePath = "D:\\sexy_danger_3.jpg";
        try {
            //请求参数
            Map<String, Object> sceneConf = new HashMap<String, Object>();
            Map<String, Object> ocrConf = new HashMap<String, Object>();
            ocrConf.put("recognize_granularity", "big");
            ocrConf.put("language_type", "CHN_ENG");
            ocrConf.put("detect_direction", true);
            ocrConf.put("detect_language", true);
            sceneConf.put("ocr", ocrConf);

            Map<String, Object> input = new HashMap<String, Object>();
            /*log_id	uint64	是	请求标识码，随机数，唯一。
            result	object	是	返回结果json串，其内包含用户要调用的各个模型服务的返回结果。
            ocr	object	否	文字识别服务返回结果。请参照下文，文字识别说明或“通用文字识别接口文档”
            politician	object	否	政治敏感识别返回结果。 请参照下文，政治敏感识别说明。
            antiporn	object	否	色情识别返回结果。请参照下文，色情识别说明。
            terror	object	否	暴恐识别返回结果。请参照下文，暴恐识别说明。
            webimage	object	否	网络图片文字识别结果。请参照下文，网络图片文字识别说明或“网络图片文字识别接口文档”
            disgust	object	否	恶心图像识别结果。请参照下文，恶心图像识别说明。
            watermark	object	否	广告检测返回结果。请参照下文，广告检测说明。
            quality	object	否	图像质量检测返回结果。请参照下文，图像质量检测说明。
            accurate	object	否	通用文字识别（高精度含位置版）返回结果。请参照下文，通用文字识别（高精度含位置版）说明或“通用文字识别（含位置高精度版）接口文档”
            accuratebasic	object	否	通用文字识别（高精度版）返回结果。请参照下文，通用文字识别（高精度版）说明或“通用文字识别（高精度版）接口文档”*/
            List<Object> scenes = new ArrayList<Object>();
            scenes.add("ocr");
            scenes.add("face");
            scenes.add("public");
            scenes.add("politician");
            scenes.add("antiporn");
            scenes.add("terror");
            scenes.add("webimage");
            scenes.add("disgust");
            scenes.add("watermark");

            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            //String imageUrl = "########网络图片地址#######";
            //input.put("imgUrl", imageUrl);//与image二者选一
            input.put("image", imgStr);//与image二者选一
            input.put("scenes", scenes);
            input.put("sceneConf", sceneConf);

            String params = GsonUtils.toJson(input);
            System.out.println(params);
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.6d10f6f10509ec53ec8016daea795e3b.2592000.1554972509.282335-15740662";
            String result = HttpUtil.post(imgCensorUrl, accessToken,"application/json",  params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
