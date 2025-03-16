package com.zwb.Wolai.model;

import com.zwb.Wolai.model.Block.Block;
import lombok.Data;

import java.util.List;

@Data
public class BlockVO {
    private String parent_id;
    private List<Block> blocks;
}
