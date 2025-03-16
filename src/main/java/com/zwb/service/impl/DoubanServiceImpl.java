package com.zwb.service.impl;

import com.zwb.pojo.DoubanBook;
import com.zwb.service.DoubanService;
import com.zwb.utils.URLUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DoubanServiceImpl implements DoubanService {
    public DoubanBook parseDoubanBook(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0")
                    .timeout(30000).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DoubanBook book = new DoubanBook();
        book.setName(document.select("#wrapper > h1 > span").first().text());
        book.setAuthor(document.select("#info > span:nth-child(1) > a").first().text());
        book.setUrl(url);

//        book.setBrief(document.select("div.intro").first().text());
        book.setBrief(document.select("div.intro").get(0).text());
        if (book.getBrief().contains("(展开全部)")) {
            book.setBrief(document.select("div.intro").get(1).text());
        }
        book.setImgUrl(document.select("#mainpic > a > img").first().attr("src"));
        book.setPublisher(document.select("#info > a:nth-child(4)").first().text());
        book.setRating(document.select("#interest_sectl > div > div.rating_self.clearfix > strong").first().text());
        System.out.println(book);
        return book;
    }

    @Override
    public List<DoubanBook> searchBook(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0")
                    .timeout(30000).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Elements resultElements = document.getElementsByClass("result");
        List<DoubanBook> books = new ArrayList<>();
        for (Element element : resultElements) {
            DoubanBook book = new DoubanBook();
            String urlString = element.getElementsByClass("nbg").first().attr("href");
            book.setUrl(URLUtils.parseDoubanBookUrl(urlString));
            book.setName(element.getElementsByClass("nbg").first().attr("title"));

            Elements ratingElements = element.getElementsByClass("rating-info").first().getElementsByTag("span");
            if (ratingElements.size() == 4) {
                book.setRating(ratingElements.get(1).text());
                book.setAudience(ratingElements.get(2).text());
                book.setAuthor(ratingElements.get(3).text());
            } else if (ratingElements.size() == 3){
                book.setRating("null");
                book.setAudience(ratingElements.get(1).text());
                book.setAuthor("");
            }

            Element briefElement = element.getElementsByTag("p").first();
            book.setBrief(briefElement == null ? null : briefElement.text());
            book.setImgUrl(element.getElementsByTag("img").first().attr("src"));
            books.add(book);
        }
        return books;
    }


//    public static void main(String[] args) {
////        String url = "https://book.douban.com/subject/36814422/?icn=index-latestbook-subject";
//        new DoubanServiceImpl().searchBook("");
//    }
}
