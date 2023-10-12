package com.zwb.model.Block;

import lombok.Data;

import java.util.Map;

@Data
public class Block {
    public String type;
    public Object content;
    public String text_alignment;
    public Map<String, Object> additional_properties;
}