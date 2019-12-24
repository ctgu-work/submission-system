package com.ctgu.contributionsystem.dto;

import com.ctgu.contributionsystem.model.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-23 20:11
 * @ClassName Article
 * @Version 1.0.0
 */
@Getter
@Setter
@ToString
public class Article {
    private Integer id;
    private String title;
    private String content;
    private List<Tag> tags;
    private String avatarUrl;
    private String author;
    private String date;
    private String classify;
    private Integer click;
    private Integer likeCount;
    private String email;
    public Article(){

    }
    public Article(Integer id, String title, String content, List<Tag> tags, String avatarUrl, String author, String date, String classify, Integer click, Integer likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.avatarUrl = avatarUrl;
        this.author = author;
        this.date = date;
        this.classify = classify;
        this.click = click;
        this.likeCount = likeCount;
    }
}
