package com.lgz.crazy.business.fdfs.controller;

import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lgz on 2019/2/14.
 */

@RestController
public class FDFSController {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/uppload")
    public StorePath test() throws IOException {

        File file = new File("D:\\color.png");
        // 设置文件信息
        Set<MataData> mataData = new HashSet<>();
        mataData.add(new MataData("author", "zonghui"));
        mataData.add(new MataData("description", "xxx文件，嘿嘿嘿"));

        // 上传   （文件上传可不填文件信息，填入null即可）
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension("aa.png"), mataData);
        return storePath;
    }
}
