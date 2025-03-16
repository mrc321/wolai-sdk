package com.zwb.pojo.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Data
@Component
public class Form {
    private String id;
    private String url;
    private String category;

    public boolean isEmpty(){
        return id == null || url == null || id.isEmpty() || url.isEmpty();
    }
}
