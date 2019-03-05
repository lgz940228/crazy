package com.lgz.crazy.common.utils;

import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.config.ftp.FTPProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lgz on 2019/3/5.
 */
@Slf4j
@Component
public class FtpUtil {

    private static FTPProperties ftpProperties;

    public FtpUtil(FTPProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    public static Res<List<String>> upload(String dir, List<MultipartFile> listFile){
        Res res = Res.getFailedResult();
        List<String> list = new ArrayList<>();
        try {
            for (MultipartFile file : listFile) {
                String originalFilename = file.getOriginalFilename();
                InputStream inputStream = file.getInputStream();
                String suffix = getSuffix(originalFilename);
                //String prefix = getPrefix(remoteFileName);
                String ftpFileName = UUID.randomUUID()+"-"+ System.currentTimeMillis() + suffix;
                dir=dir.startsWith("/uploads")?dir:dir.startsWith("/")?"/uploads"+dir:"/uploads/"+dir;
                if(!uploadFile(dir,ftpFileName,inputStream)){
                    res.setMsg("图片上传失败");
                    return res;
                }
                dir=dir.endsWith("/")?dir:dir+"/";
                String fileUrl = dir+ftpFileName;
                list.add(fileUrl);
            }
            return Res.getSuccessResult(list,"上传成功");
        }catch (Exception e){
            e.printStackTrace();
            res.setMsg("图片上传异常e="+e.getMessage());
        }
        return res;

    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @Version1.0
     * @param remoteFileName
     *      上传到FTP服务器上的文件名
     * @param input
     *      本地文件流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String dir,String remoteFileName, InputStream input) {
        boolean result = false;
        FTPClient ftpClient = null;
        try {
            ftpClient = makeObject();
            ftpClient.enterLocalPassiveMode();
            if(!ftpClient.changeWorkingDirectory(dir)){
                throw new RuntimeException("切换目录失败dir="+dir);
            }
            result = ftpClient.storeFile(remoteFileName, input);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(input!= null){
                    input.close();
                }
                //disconnect(ftpClient);
                destroyObject(ftpClient);
            }catch (Exception e){
                log.error("流关闭异常",e);
            }
        }
        return result;
    }

    private static String getSuffix(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx > -1) {
            return fileName.substring(idx);
        }
        return "";
    }

    public static FTPClient makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding(ftpProperties.getEncoding());
        ftpClient.setConnectTimeout(ftpProperties.getClientTimeout());
        try {
            ftpClient.connect(ftpProperties.getHost(), ftpProperties.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                log.warn("FTPServer refused connection");
                return null;
            }
            boolean result = ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            ftpClient.setFileType(ftpProperties.getTransferFileType());
            if (!result) {
                log.warn("ftpClient login failed... username is {}", ftpProperties.getUsername());
            }
        } catch (Exception e) {
            log.error("create ftp connection failed...{}", e);
            throw e;
        }
        return ftpClient;
    }

    public static void destroyObject(FTPClient ftpClient){
        try {
            if(ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                //ftpClient.disconnect();
            }
        } catch (Exception e) {
            log.error("ftp client logout failed...{}", e);
            //throw e;
        } finally {
            try {
                if(ftpClient != null) {
                    ftpClient.disconnect();
                }
            }catch (Exception e){
                log.error("ftp client disconnect failed...{}", e);
            }

        }

    }
}
