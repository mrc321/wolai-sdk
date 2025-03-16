package com.zwb.model.Block;

import com.zwb.model.base.richtext.CreateRichText;
import lombok.Data;

@Data
public class Title extends Block{

    private final String type = "heading";

    private Integer level;

    private boolean toggle;
}
