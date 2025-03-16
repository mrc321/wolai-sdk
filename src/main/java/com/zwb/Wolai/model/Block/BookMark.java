package com.zwb.Wolai.model.Block;

import lombok.Data;

@Data
public class BookMark extends Block{
    private final String type = "bookmark";
    private String link;
}
