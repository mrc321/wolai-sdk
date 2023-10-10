package com.zwb.model;

import lombok.Data;
import java.util.List;

@Data
public class CreateRichText {
    private List<Object> elements;

    @Data
    public static class RichTextElement {
        private String type;  // 可选, 如果是纯文本，则此字段可以不传递
        private String title; // 文本内容
        private Boolean bold; // 是否加粗
    }
}

