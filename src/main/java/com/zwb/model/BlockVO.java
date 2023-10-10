package com.zwb.model;

import lombok.Data;

import java.util.Map;

@Data
public class BlockVO {
    private String parent_id;
    private Block[] blocks;

    @Data
    public class Page {
        private String type;  // 必填
        private Object icon;  // 可选，具体类型需要根据实际需求定义
        private Object page_cover;  // 可选，具体类型需要根据实际需求定义
        private Object page_setting;  // 可选，具体类型需要根据实际需求定义
        private Object content;  // 可选，具体类型需要根据实际需求定义
    }



    @Data
    public static class CodeBlock {
        private String type = "code"; // 固定为 "code"
        private CodeLanguage language;
        private CodeSetting codeSetting;
        private String caption;
        private CreateRichText content;

        @Data
        public static class CodeLanguage {
            private String value; // 这里可以是 "text", "ybsz", "latex", "c", "r", "csharp", ... 等等
        }

        @Data
        public static class CodeSetting {
            private Boolean line_number;
            private Boolean line_break;
            private Boolean ligatures;
            private String preview_format; // "both" 或 "code" 或 "mermaid"
        }
    }

    @Data
    public static class QuoteBlock {
        private String type;
        private CreateRichText content;
    }

    @Data
    public static class CalloutBlock {
        private String type;
        private Object icon;
        private Boolean marquee_mode;
        private CreateRichText content;
    }

    @Data
    public static class MediaBlock {
        private String type;
        private String link;
        private String caption;
    }

    @Data
    public static class DividerBlock {
        private String type;
    }

    @Data
    public static class ProgressBarBlock {
        private String type;
        private Integer progress;
        private Boolean auto_mode;
        private Boolean hide_number;
    }

    @Data
    public static class BookmarkBlock {
        private String type;
        private String link;
    }

    @Data
    public static class EnumListBlock {
        private String type;
        private CreateRichText content;
    }

    @Data
    public static class TodoListBlock {
        private String type;
        private CreateRichText content;
        private Boolean checked;
    }

    @Data
    public static class TodoListProBlock {
        private String type;
        private TodoListProStatus task_status;
        private CreateRichText content;
    }

    @Data
    public static class BullListBlock {
        private String type;
        private CreateRichText content;
    }

    @Data
    public static class ToggleListBlock {
        private String type;
        private CreateRichText content;
    }

    @Data
    public static class BlockEquation {
        private String type;
        private CreateRichText content;
    }

    @Data
    public static class EmbedBlock {
        private String type;
        private String original_link;
        private String embed_link;
    }

    @Data
    public static class Block {
        private String type;
        private Object content;
        private String text_alignment;
        private Map<String, Object> additional_properties;
    }

    @Data
    public static class TextContent {
        private String text;
    }

    @Data
    public static class HeadingContent {
        private String title;
        private String front_color;
    }

    public enum TodoListProStatus {
        TODO, DOING, DONE, CANCEL
    }
}
