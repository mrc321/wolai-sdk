package com.zwb.model.Block;

import com.zwb.model.base.richtext.CreateRichText;
import com.zwb.model.base.LinkCover;
import com.zwb.model.base.PageSetting;
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
    private Object content;
}

