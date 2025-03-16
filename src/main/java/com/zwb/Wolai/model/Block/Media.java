package com.zwb.Wolai.model.Block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media extends Block{
    //媒体块类型，可能的值有 "image","video","audio"
    private final String type = "image";
    private String link;
    private String caption;
}
