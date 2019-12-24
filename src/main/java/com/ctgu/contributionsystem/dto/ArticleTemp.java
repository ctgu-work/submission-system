package com.ctgu.contributionsystem.dto;

import com.ctgu.contributionsystem.model.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-23 21:05
 * @ClassName ArticleTemp
 * @Version 1.0.0
 */
@Getter
@Setter
@ToString
public class ArticleTemp implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String avatarUrl;
    private String author;
    private Timestamp submitTime;
    private String classify;
    private Integer click;
    private Integer likeCount;
    public ArticleTemp(){

    }
    public ArticleTemp(Integer id, String title, String content, String avatarUrl, String author, Timestamp submitTime, String classify, Integer click, Integer likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.avatarUrl = avatarUrl;
        this.author = author;
        this.submitTime = submitTime;
        this.classify = classify;
        this.click = click;
        this.likeCount = likeCount;
    }
}
