package com.zwb.Wolai.model.base;

import lombok.Data;

@Data
public class PageSetting {
    private boolean isFullWidth;
    private boolean isSmallText;
    private boolean hasFloatingCatalog;
    private PageFontFamily fontFamily = PageFontFamily.DEFAULT;
    private PageLineLeading lineSpacing = PageLineLeading.DEFAULT;
}

enum PageFontFamily {
    DEFAULT("default"),
    SIMSUN("simsun"),
    KAITI("kaiti");

    private final String value;

    PageFontFamily(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

enum PageLineLeading {
    DEFAULT("default"),
    LOOSE("loose"),
    COMPACT("compact");

    private final String value;

    PageLineLeading(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
