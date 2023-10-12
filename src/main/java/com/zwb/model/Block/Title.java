package com.zwb.model.Block;

import com.zwb.model.base.richtext.CreateRichText;
import lombok.Data;

@Data
public class Title extends Block{

    public final String type = "heading";

    public Integer level;

    public boolean toggle;

    public Object content;
}
