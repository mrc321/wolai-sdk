package com.zwb.service.impl;

import com.zwb.Wolai.block.BlockHandler;
import com.zwb.Wolai.model.Block.*;
import com.zwb.Wolai.model.BlockVO;
import com.zwb.Wolai.model.base.richtext.RichText;
import com.zwb.pojo.DoubanBook;
import com.zwb.pojo.vo.Form;
import com.zwb.service.DoubanService;
import com.zwb.service.WolaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WolaiServiceImpl implements WolaiService {
    @Autowired
    DoubanService doubanService;

    @Override
    public void createBookContent(Form form) {
        DoubanBook doubanBook = doubanService.parseDoubanBook(form.getUrl());
        doubanBook.setCategory(form.getCategory());
        List<String> childIds = BlockHandler.getBlockChildrenById(form.getId(), null, null).get(0).getChildren().getIds();

        BlockHandler.createBlock(buildLeft(doubanBook, childIds.get(0)));
        BlockHandler.createBlock(buildRight(doubanBook, childIds.get(1)));
    }


    private static BlockVO buildLeft(DoubanBook doubanBook, String parentId){
        BlockVO blockVO = new BlockVO();
        blockVO.setParent_id(parentId);
        ArrayList<Block> blocks = new ArrayList<>();

        BookMark bookDouban = new BookMark();
        bookDouban.setLink(doubanBook.getUrl());
        blocks.add(bookDouban);

        blocks.add(new Media(doubanBook.getImgUrl(),null));


        Text bookName = new Text();
        bookName.getContent().add(new RichText(doubanBook.getName(),"gray",null));
        blocks.add(bookName);

        Text bookRating = new Text();
        bookRating.getContent().add(new RichText("评分：" + doubanBook.getRating(),"gray",null));
        blocks.add(bookRating);

        Text bookType = new Text();
        bookType.getContent().add(new RichText("类型：" + doubanBook.getCategory(),"gray",null));
        blocks.add(bookType);

        Text bookAuthor = new Text();
        bookAuthor.getContent().add(new RichText("作者：" + doubanBook.getAuthor(),"gray",null));
        blocks.add(bookAuthor);

        Text bookPublish = new Text();
        bookPublish.getContent().add(new RichText("出版社：" + doubanBook.getPublisher(),"gray",null));
        blocks.add(bookPublish);


        Title bookStateTitle = new Title();
        bookStateTitle.setLevel(3);
        bookStateTitle.getContent().add(new RichText("状态",null,null));
        bookStateTitle.setBlock_back_color("cultured_background");
        blocks.add(bookStateTitle);

        Text bookState = new Text();
        bookState.getContent().add(new RichText("未读·—·想读·—·在读·—·已读","gray",null));
        blocks.add(bookState);

        Title bookImportantTitle = new Title();
        bookImportantTitle.setLevel(3);
        bookImportantTitle.getContent().add(new RichText("重要性","null",null));
        bookImportantTitle.setBlock_back_color("cultured_background");
        blocks.add(bookImportantTitle);

        Title bookImportant = new Title();
        bookImportant.setLevel(1);
        bookImportant.getContent().add(new RichText("<<<<<\uD83D\uDEB4\uD83C\uDFFB","gray",null));
        blocks.add(bookImportant);

        Text bookTip = new Text();
        bookTip.getContent().add(new RichText("\uD83D\uDCA1 读书，照亮前方的路","gray",null));
        blocks.add(bookTip);

        blockVO.setBlocks(blocks);
        return blockVO;
    }

    private static BlockVO buildRight(DoubanBook doubanBook, String parentId){
        BlockVO blockVO = new BlockVO();
        blockVO.setParent_id(parentId);

        ArrayList<Block> blocks = new ArrayList<>();

        Text bookBrief = new Text();
        bookBrief.getContent().add(new RichText("\t" + doubanBook.getBrief(),null,null));
        blocks.add(bookBrief);

        Title bookImportantTitle = new Title();
        bookImportantTitle.setLevel(3);
        bookImportantTitle.getContent().add(new RichText("记录","null",null));
        bookImportantTitle.setBlock_back_color("cultured_background");
        blocks.add(bookImportantTitle);

        blockVO.setBlocks(blocks);
        return blockVO;
    }
}
