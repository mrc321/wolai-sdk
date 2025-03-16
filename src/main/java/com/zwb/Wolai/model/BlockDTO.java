package com.zwb.Wolai.model;

import lombok.Data;
import java.util.List;

@Data
public class BlockDTO {
    private String id;
    private String parentId;
    private String parentType;
    private String pageId;
    private Children children;
    private String type;
    private List<RichText> content;
    private int version;
    private String createdBy;
    private long createdAt;
    private String editedBy;
    private long editedAt;
    // 可选字段
    private String blockFrontColor;
    private String blockBackColor;
    private String textAlignment;
    private String blockAlignment;

    @Data
    public static class Children {
        private List<String> ids;
        private String apiUrl;
    }

    @Data
    public static class RichText {
        private String title;
        private String type;
    }
}
