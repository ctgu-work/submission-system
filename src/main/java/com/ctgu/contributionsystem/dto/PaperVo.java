package com.ctgu.contributionsystem.dto;

import com.ctgu.contributionsystem.model.Paper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author : kun
 * @date ： 2019/12/24
 * @description ：this is a code
 **/

@Getter
@Setter
public class PaperVo {

    private Integer paperId;
    private String title;
    private String content;
    private String author;
    private Integer category;
    private Integer clickRate;
    private Integer likeCount;
    private Integer status;
    private Integer userId;
    private Timestamp submitTime;
    private String description;
    private String avatar_url;

    public PaperVo() {
        this.paperId = paperId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.clickRate = clickRate;
        this.likeCount = likeCount;
        this.status = status;
        this.userId = userId;
        this.submitTime = submitTime;
        this.description = description;
        this.avatar_url = avatar_url;
    }
}
