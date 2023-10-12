package com.zwb.model.base.richtext;
import lombok.Data;

@Data
public class RichText implements RichTextElement {
    // "text" 或 "equation"
    private String type;
    private String title;
    private Boolean bold;
}
