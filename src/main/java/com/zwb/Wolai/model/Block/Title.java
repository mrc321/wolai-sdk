package com.zwb.Wolai.model.Block;

import lombok.Data;

@Data
public class Title extends Block{

    private final String type = "heading";

    private Integer level;

    private boolean toggle;
}
