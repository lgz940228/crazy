package com.lgz.crazy.ftp;

import com.lgz.crazy.config.ftp.FTPUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpClientTest {

    /**
     * 测试上传
     */
    @Test
    public void uploadFile(){
        boolean flag = FTPUtils.uploadFile("/","1.png", "D:\\photo\\1.png");
        System.out.println(flag);
    }
    /**
     * 测试上传
     * https://www.jb51.net/article/153276.htm
     */
    @Test
    public void a(){
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        try {
            //默认端口号：21
            ftpClient.connect("39.105.206.67");
            ftpClient.login("ftp_test", "ftp123456");
            fis = new FileInputStream("D:\\1.png");
            // 设置上传目录
            ftpClient.changeWorkingDirectory("/");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            //二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile("第三方.png", fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }
}
