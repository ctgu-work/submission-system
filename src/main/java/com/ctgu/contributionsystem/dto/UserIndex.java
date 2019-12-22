package com.ctgu.contributionsystem.dto;

import com.ctgu.contributionsystem.model.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 20:58
 * @ClassName UserIndex
 * @Version 1.0.0
 */
@Getter
@Setter
@ToString
public class UserIndex {
    private int articleWaitNumber;//待审核的稿件数
    private int articleAcceptNumber;//通过的稿件数
    private int articleNotAcceptNumber;//没有通过的稿件数
    private int articleMoney;//稿费
    private int userLikeCount;//点赞数
    private int userClickCount;//点击量
    List<Tag>uerHotTag;
}
