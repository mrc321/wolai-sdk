package com.zwb.Wolai.model.Block;

import com.zwb.Wolai.model.base.richtext.RichText;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Block {
    private String type;
//    public Object content;
private List<RichText> content = new ArrayList<>();
    private String text_alignment;
    //被背景颜色
    private String block_back_color;
    private Map<String, Object> additional_properties;
}