package com.zwb.Wolai.model.base.richtext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RichText implements RichTextElement {
    // "text" 或 "equation"
    private String title;
    private String front_color;
    private Boolean bold;

}
