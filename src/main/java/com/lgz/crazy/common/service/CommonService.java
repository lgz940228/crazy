package com.lgz.crazy.common.service;

import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.entities.SysDictionary;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lgz on 2019/3/15.
 */
public interface CommonService {
    Res<List<String>> handleFileUpload(HttpServletRequest request);

    ResponseEntity<byte[]> getKaptchaImage(HttpSession session);

    Res<String[]> ObtainLatAndLng(String addr);

    Res<List<SysDictionary>> querySysDictionary(String status,String dictType);
}
