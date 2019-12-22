package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:49
 * @ClassName ArticleStatus
 * @Version 1.0.0
 */
@Getter
@Setter
@ToString
public class ArticleStatus {
    private String title;//标题
    private String status;//状态
    private String reviewContent;//评语
    private String reviewSpecialist;//审核人

}
