package com.zwb.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class URLUtils {
    public static List<String> extractBlockIds(String jsonString) {
        System.out.println(jsonString);
        // 解析JSON字符串以获取数据数组
        JSONArray jsonArray = JSONUtil.parseObj(jsonString).getJSONArray("data");

        List<String> blockIds = new ArrayList<>();
        for (Object urlObject : jsonArray) {
            String url = (String) urlObject;

            // 获取#号后面的块ID
            int hashIndex = url.indexOf("#");
            if (hashIndex != -1) {
                String blockId = url.substring(hashIndex + 1);
                blockIds.add(blockId);
            } else {
                // 如果没有#，则取最后一个/后面的内容作为ID
                int lastSlashIndex = url.lastIndexOf("/");
                if (lastSlashIndex != -1 && lastSlashIndex < url.length() - 1) {
                    String blockId = url.substring(lastSlashIndex + 1);
                    blockIds.add(blockId);
                }
            }
        }
        return blockIds;
    }

}
