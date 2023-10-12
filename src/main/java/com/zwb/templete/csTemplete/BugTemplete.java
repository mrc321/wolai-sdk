package com.zwb.templete.csTemplete;

import com.zwb.block.BlockHandler;
import com.zwb.model.Block.*;
import com.zwb.model.BlockVO;
import com.zwb.model.base.richtext.CreateRichText;

import java.util.ArrayList;
import java.util.List;

import static com.zwb.block.BlockHandler.createBlock;
import static com.zwb.constant.HeadingLevelConstant.ONE;
import static com.zwb.utils.URLUtils.extractBlockIds;


public class BugTemplete extends BlockVO implements Templete {
    public BugTemplete(){
        List<Block> blockList = new ArrayList<>();
        Title title1 = new Title();
        title1.setLevel(ONE);
        title1.setContent("bug详情");
        Text bugDetail = new Text();
        bugDetail.setContent("请描述你的bug");
        Title title2 = new Title();
        title2.setLevel(ONE);
        title2.setContent("参考资料");
        Quote quote = new Quote();
        quote.setContent("在这里写你的参考资料");
        System.out.println(bugDetail.getContent());
        blockList.add(title1);
        blockList.add(bugDetail);
        blockList.add(title2);
        blockList.add(quote);
        this.setBlocks(blockList);
    }

    @Override
    public void createTemplete(String parentId) {
        Page page = new Page();
        page.setContent("Bug详情模板");
        BlockVO blockVO = new BlockVO();
        blockVO.setParent_id(parentId);
        List<Block> blocks = new ArrayList<>();
        blocks.add(page);
        blockVO.setBlocks(blocks);
        String pageId = extractBlockIds(createBlock(blockVO)).get(0);
        BlockVO bugTemplete = new BugTemplete();
        bugTemplete.setParent_id(pageId);
        // 调用 createBlock 方法发送请求
        String result = createBlock(bugTemplete);
        System.out.println("创建块的结果： " + result);

    }
}
