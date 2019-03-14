package com.lgz.crazy.common.utils.poi;

import com.lgz.crazy.common.utils.StreamUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lgz on 2019/3/14.
 */
@Controller
@RequestMapping("/poi/")
public class PoiTestController {
    @RequestMapping("pdf")
    @ResponseBody
    public String downloadPdf(HttpServletResponse response) throws IOException {
        // 生成的文件路径前缀
        String filePrefix = PoiTestController.class.getResource("/").getPath();
        String filePath = filePrefix+"poi/month_payment.docx";

        File f = new File(filePath);
        Map<String,String> map = new HashMap<>();
        map.put("saleNo","123456");
        map.put("companyName","lgz");
        map.put("memberName","gzx");
        OutputStream outputStream = WorderToNewWordUtils.render(filePath, f, map);
        if (outputStream == null) {
            return "fail";
        }
        //设置响应头
       /* response.setHeader("content-disposition","attachment;filename=" + "aa.pdf");
        response.setHeader("content-type", "application/pdf");*/
        response.getOutputStream().write(((ByteArrayOutputStream) outputStream).toByteArray());
        //String enName = new String(pdfTitle.getBytes("gbk"),"ISO-8859-1");
        StreamUtil.close(outputStream);
        return "success";
    }


}
