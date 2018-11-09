package com.xa.dz.ims.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/9 18:19
 * @Description
 * @Copyright Copyright 2018/11/9 18:19 BOCO. All rights reserved
 */
@Component
public class Base64 {

    Logger logger = LoggerFactory.getLogger(Base64.class);

    //加密
    public String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Base64加密异常:", e);
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                logger.error("Base64解密异常:", e);
            }
        }
        return result;
    }
}
