package com.zwb.model.Block;

import com.zwb.model.base.richtext.CreateRichText;
import lombok.Data;

@Data
public class Text extends Block {

    public final String type = "text";
    /**
     *内容
     */
    public Object content;
    /**
     *是否折叠
     */
    public boolean toggle;
}
