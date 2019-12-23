package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-23 18:59
 * @ClassName HotArtcle
 * @Version 1.0.0
 */
@Getter
@ToString
@Setter
public class HotArtcle {
    Integer id;
    String date;
    String title;
    Integer click;
    Integer like;
    public HotArtcle(Integer id, String date, String title, Integer click, Integer like){
        this.id = id;
        this.date = date;
        this.click = click;
        this.title = title;
        this.like = like;
    }
}
