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

    // 在这里，我将 icon 字段的类型设置为 Object。
    // 你可以在设置值之前进行类型检查，
    // 或者你也可以定义一个共同的接口或基类，让 LinkIcon 和 EmojiIcon 都实现/继承它。

    public void setIcon(Object icon) {
        // 在这里，你可以添加一些逻辑来检查 icon 是否是 LinkIcon 或 EmojiIcon
        this.icon = icon;
    }
}

