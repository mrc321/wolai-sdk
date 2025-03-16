package com.zwb.service;

import com.zwb.pojo.DoubanBook;

import java.util.ArrayList;
import java.util.List;

public interface DoubanService{
    //解析豆瓣书籍信息
     DoubanBook parseDoubanBook(String url);

    List<DoubanBook> searchBook(String url);
}
