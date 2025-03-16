package com.zwb.model.base.richtext;

import lombok.Data;

@Data
public class RichTextStringAdapter implements RichTextElement {
    private final String text;

    public RichTextStringAdapter(String text) {
        this.text = text;
    }
}
