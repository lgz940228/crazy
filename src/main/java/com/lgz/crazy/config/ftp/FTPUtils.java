package com.lgz.crazy.config.ftp;

import com.lgz.crazy.common.entities.Res;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lgz on 2019/2/27.
 */
@Slf4j
//@Component
public class FTPUtils {

    /**
     * FTP的连接池
     */
    @Autowired
    public static FTPClientPool ftpClientPool;

    @Autowired
    private FTPProperties ftpProperties;

    /**
     * 初始化设置
     * @return
     */
    //@PostConstruct
    public boolean init() {
        FTPClientFactory factory = new FTPClientFactory(ftpProperties);
        try {
            ftpClientPool = new FTPClientPool(factory);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取连接对象
     * @return
     * @throws Exception
     */
    public static FTPClient getFTPClient() throws Exception {
        //初始化的时候从队列中取出一个连接
        FTPClient ftpClient = null;
        synchronized (FTPUtils.class) {
            ftpClient = ftpClientPool.borrowObject();
        }
        return ftpClient;
    }


    /**
     * 当前命令执行完成命令完成
     * @throws IOException
     */
    public void complete(FTPClient ftpClient) throws IOException {
        ftpClient.completePendingCommand();
    }

    /**
     * 当前线程任务处理完成，加入到队列的最后
     * @return
     */
    public static void disconnect(FTPClient ftpClient) throws Exception {
        ftpClientPool.returnObject(ftpClient);
    }

    public static Res<List<String>> upload(String dir,List<MultipartFile> listFile){
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
                if(!FTPUtils.uploadFile(dir,ftpFileName,inputStream)){
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

    private static String getPrefix(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx > -1) {
            return fileName.substring(0, idx);
        }
        return fileName;
    }

    private static String getSuffix(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx > -1) {
            return fileName.substring(idx);
        }
        return "";
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
            ftpClient = getFTPClient();
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
                disconnect(ftpClient);
            }catch (Exception e){
                log.error("流关闭异常",e);
            }
        }
        return result;
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @Version1.0
     * @param remoteFileName
     *      上传到FTP服务器上的文件名
     * @param localFile
     *      本地文件
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String dir,String remoteFileName, String localFile){
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File(localFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uploadFile(dir,remoteFileName, input);
    }

    /**
     * 拷贝文件
     * @param fromFile
     * @param toFile
     * @return
     * @throws Exception
     */
    /*public boolean copyFile(String fromFile, String toFile) throws Exception {
        InputStream in=getFileInputStream(fromFile);
        getFTPClient();
        boolean flag = ftpClient.storeFile(toFile, in);
        in.close();
        return flag;
    }*/

    /**
     * 获取文件输入流
     * @param fileName
     * @return
     * @throws IOException
     */
    /*public static InputStream getFileInputStream(String fileName) throws Exception {
        ByteArrayOutputStream fos=new ByteArrayOutputStream();
        getFTPClient();
        ftpClient.retrieveFile(fileName, fos);
        ByteArrayInputStream in=new ByteArrayInputStream(fos.toByteArray());
        fos.close();
        return in;
    }*/

    /**
     * Description: 从FTP服务器下载文件
     *
     * @Version1.0
     * @return
     */
    /*public static boolean downFile(String remoteFile, String localFile){
        boolean result = false;
        try {
            getFTPClient();
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(remoteFile, os);
            ftpClient.logout();
            ftpClient.disconnect();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }*/

    /**
     * 从ftp中获取文件流
     * @param filePath
     * @return
     * @throws Exception
     */
    /*public static InputStream getInputStream(String filePath) throws Exception {
        getFTPClient();
        InputStream inputStream = ftpClient.retrieveFileStream(filePath);
        return inputStream;
    }*/

    /**
     * ftp中文件重命名
     * @param fromFile
     * @param toFile
     * @return
     * @throws Exception
     */
    /*public boolean rename(String fromFile,String toFile) throws Exception {
        getFTPClient();
        boolean result = ftpClient.rename(fromFile,toFile);
        return result;
    }*/

    /**
     * 获取ftp目录下的所有文件
     * @param dir
     * @return
     */
    /*public FTPFile[] getFiles(String dir) throws Exception {
        getFTPClient();
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftpClient.listFiles(dir);
        }catch (Throwable thr){
            thr.printStackTrace();
        }
        return files;
    }*/

    /**
     * 获取ftp目录下的某种类型的文件
     * @param dir
     * @param filter
     * @return
     */
    /*public FTPFile[] getFiles(String dir, FTPFileFilter filter) throws Exception {
        getFTPClient();
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftpClient.listFiles(dir, filter);
        }catch (Throwable thr){
            thr.printStackTrace();
        }
        return files;
    }*/

    /**
     * 创建文件夹
     * @param remoteDir
     * @return 如果已经有这个文件夹返回false
     */
   /* public boolean makeDirectory(String remoteDir) throws Exception {
        getFTPClient();
        boolean result = false;
        try {
            result = ftpClient.makeDirectory(remoteDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/

   /* public boolean mkdirs(String dir) throws Exception {
        boolean result = false;
        if (null == dir) {
            return result;
        }
        getFTPClient();
        ftpClient.changeWorkingDirectory("/");
        StringTokenizer dirs = new StringTokenizer(dir, "/");
        String temp = null;
        while (dirs.hasMoreElements()) {
            temp = dirs.nextElement().toString();
            //创建目录
            ftpClient.makeDirectory(temp);
            //进入目录
            ftpClient.changeWorkingDirectory(temp);
            result = true;
        }
        ftpClient.changeWorkingDirectory("/");
        return result;
    }*/
}