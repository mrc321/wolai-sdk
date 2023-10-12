package com.zwb.model.Block;

import lombok.Data;

/**
 * @author zwb
 */
@Data
public class Quote extends Block {

    public final String type = "quote";
    /**
     *内容
     */
    public Object content;
}
