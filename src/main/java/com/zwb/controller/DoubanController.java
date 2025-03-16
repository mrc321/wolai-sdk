package com.zwb.controller;


import com.zwb.common.R;
import com.zwb.pojo.DoubanBook;
import com.zwb.pojo.vo.Form;
import com.zwb.service.DoubanService;
import com.zwb.service.WolaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoubanController {
    @Autowired
    WolaiService wolaiService;

    @Autowired
    DoubanService doubanService;

    @GetMapping({"/","/index"})
    public String index(){
        return "douban";
    }

    @PostMapping("/book/import/douban")
    @ResponseBody
    public R importBookInfoFromDoubanToWolai(@RequestBody Form form){
        if (form.isEmpty()) {
            return R.ok().message("请求内容为空");
        }
        wolaiService.createBookContent(form);
        return R.ok().message("执行成功");
    }


    @GetMapping("/search/{cat}/{keyword}")
    @ResponseBody
    public R searchDouban(@PathVariable("cat") String cat, @PathVariable("keyword") String keyword){
        String url = "https://www.douban.com/search?cat=" + cat +"&q=" + keyword;
        List<DoubanBook> doubanBooks = doubanService.searchBook(url);
        return R.ok().data("books",doubanBooks);
    }
}
