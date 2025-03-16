package com.zwb.Wolai.model.Block;

import com.zwb.Wolai.model.base.LinkCover;
import com.zwb.Wolai.model.base.PageSetting;
import lombok.Data;


/**
 * @author zwb
 */
@Data
public class Page extends Block{
    private final String type = "page";
    /**
     * 可以是 LinkIcon 或 EmojiIcon
     * */
    private Object icon;
    private LinkCover pageCover;
    private PageSetting pageSetting;
}

