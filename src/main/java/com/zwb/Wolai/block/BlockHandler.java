package com.zwb.Wolai.block;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zwb.Wolai.model.Block.Block;
import com.zwb.Wolai.model.Block.Page;
import com.zwb.Wolai.model.Block.Quote;
import com.zwb.Wolai.model.Block.Text;
import com.zwb.Wolai.model.BlockDTO;
import com.zwb.Wolai.model.BlockVO;
import com.zwb.Wolai.model.base.richtext.RichText;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zwb.Wolai.constant.constant.BASE_URL;
import static com.zwb.Wolai.constant.constant.token;

@Slf4j
public class BlockHandler {
    /**
     * 查询指定块的详细信息
     * @param id
     * @return
     */
    public static BlockDTO getBlockById(String id) {
        String url = BASE_URL + "blocks/" + id;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String bodyString = response.body().string();
                JSONObject jsonResponse = JSONUtil.parseObj(bodyString);
                JSONObject blockData = jsonResponse.getJSONObject("data");

                // 将 JSON 对象转换为 Block 对象
                BlockDTO block = JSONUtil.toBean(blockData.toString(), BlockDTO.class);
                return block;

            } else {
                System.out.println(response.body());
                System.out.println("请求失败，状态码：" + response.code());
            }
        } catch (IOException e) {
            System.out.println("网络错误：" + e.getMessage());
        }
        return null;
    }

    /**
     * 查询接口一次性最多获取200条记录，超过时需指定start_cursor和page_size进行分页查询
     * @param id
     * @param startCursor 从上一个响应中返回的cursor，用于请求下一页的结果。
     *                    默认值： undefined，表示从列表的开始返回结果。
     * @param pageSize 响应中需要的完整列表中的项目数量。
     *                 默认值：200 最多：200 响应可能包含少于这个数量的结果。
     * @return 子节点列表
     */
    public static List<BlockDTO> getBlockChildrenById(String id, String startCursor, Integer pageSize) {
        List<BlockDTO> childrenList = new ArrayList<>();

        String url = BASE_URL + "blocks/" + id + "/children";
        if (startCursor != null) {
            url += "?start_cursor=" + startCursor + "&page_size=" + pageSize;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String bodyString = response.body().string();
                System.out.println(bodyString);
                JSONObject jsonResponse = JSONUtil.parseObj(bodyString);
                JSONArray childrenData = jsonResponse.getJSONArray("data");

                for (Object obj : childrenData) {
                    JSONObject childData = (JSONObject) obj;
                    BlockDTO childBlock = JSONUtil.toBean(childData.toString(), BlockDTO.class);
                    childrenList.add(childBlock);
                }

                String nextCursor = jsonResponse.getStr("next_cursor");
                boolean hasMore = jsonResponse.getBool("has_more");

                if (hasMore && nextCursor != null) {
                    List<BlockDTO> nextPageChildren = getBlockChildrenById(id, nextCursor, pageSize);
                    childrenList.addAll(nextPageChildren);
                }

            } else {
                log.error("请求失败，状态码：" + response.code());
                log.error("错误信息：" + response.body());
            }
        } catch (IOException e) {
            System.out.println("网络错误：" + e.getMessage());
        }

        return childrenList;
    }

    /**
     * 创建 Block
     * @param blockVO
     * @return
     */
    public static String createBlock(BlockVO blockVO) {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "blocks";

        // 将 Java 对象转换为 JSON 字符串
        String json = JSONUtil.toJsonStr(blockVO);

        // 输出发送的 JSON 数据以便于调试
        System.out.println("发送的JSON数据: " + json);

        // 创建请求体
        RequestBody requestBody = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"),
                json);

        // 创建 HTTP 请求
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = null;
                if (response.body() != null) {
                    responseBody = response.body().string();
                }
                System.out.println(responseBody);
                return responseBody;
            } else {
                log.error(response.toString());
                return "请求失败，状态码：" + response.code();
            }
        } catch (IOException e) {
            return "网络错误：" + e.getMessage();
        }
    }


    /**
     * 批量创建单一类型的 Block
     * @param blockType 块类型，可选值：page, quote, text
     * @param nameList 块名称列表
     * @param parent_id 父块 ID
     * @return 创建是否成功
     */
    public static Boolean batchGenerateBlocks(String blockType, List<String> nameList, String parent_id) {
        BlockVO blockVO = new BlockVO();
        blockVO.setParent_id(parent_id);
        List<Block> blockList = new ArrayList<>();

        for (String name : nameList) {
            Block block = null;
            switch (blockType) {
                case "page":
                    Page pageBlock = new Page();
                    pageBlock.getContent().add(new RichText(name, null,null));
                    block = pageBlock;
                    break;
                case "quote":
                    Quote quoteBlock = new Quote();
                    quoteBlock.getContent().add(new RichText(name, null,null));
                    block = quoteBlock;
                    break;
                case "text":
                    Text textBlock = new Text();
                    textBlock.getContent().add(new RichText(name, null,null));
                    block = textBlock;
                    break;
                default:
                    System.out.println("Invalid block type: " + blockType);
                    return false;
            }
            if (block != null) {
                blockList.add(block);
            }
        }

        blockVO.setBlocks(blockList);
        createBlock(blockVO);
        return true;
    }




//    public static void main(String[] args) {
////        获取块信息demo
////        BlockDTO block = getBlockById("vib6yF1KQfYUqL3aoA84wk");
////        System.out.println(block.getBlockAlignment());
////        System.out.println(block.getChildren());
////        System.out.println(block.getContent());
////        List<BlockDTO> childBlocks = getBlockChildrenById("vib6yF1KQfYUqL3aoA84wk",null,100);
////        System.out.println("---- Block List ----");
////        for (BlockDTO child : childBlocks) {
////            System.out.println("Block ID: " + child.getId());
////            System.out.println("Block Content: " + child.getContent());
////            System.out.println("--------------------");
////        }
//
////        生成模板demo
////        BugTemplete bugTemplete = new BugTemplete();
////        bugTemplete.createTemplete("vib6yF1KQfYUqL3aoA84wk");
//
//        //批量创建demo
//        List<String> javaTopics = Arrays.asList("Java 基础", "Spring Framework", "Hibernate", "springboot",
//                "springcloud", "Maven", "JUnit", "Log4j", "Java 8 Features", "JVM",
//                "网络基础知识", "git", "swigger", "RESTful api","redis",
//                "Java 设计模式");
//
//        // 调用批量生成块的方法，类型设置为"page"
//        Boolean result = batchGenerateBlocks("page", javaTopics, "wo2srfwK3GHmxHhUEc4uWp");
//        System.out.println(result);
//
//    }
}
