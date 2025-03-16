package com.zwb.model.Block;

import com.zwb.model.base.richtext.CreateRichText;
import lombok.Data;

@Data
public class Text extends Block {

    private final String type = "text";
    /**
     *是否折叠
     */
    private boolean toggle;
}
