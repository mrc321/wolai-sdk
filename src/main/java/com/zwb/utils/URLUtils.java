package com.zwb.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class URLUtils {
    public static String parseDoubanBookUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
            //https://www.douban.com/link2/?url=https%3A%2F%2Fbook.douban.com%2Fsubject%2F4913064%2F&query=%E6%B4%BB%E7%9D%80&cat_id=1001&type=search&pos=0
            String encodedUrlParam = url.getQuery().split("url=")[1].split("&")[0]; // 获取url参数并解码
            return URLDecoder.decode(encodedUrlParam, StandardCharsets.UTF_8.name());
        } catch (
                MalformedURLException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
