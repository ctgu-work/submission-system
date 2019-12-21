package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private int articleWaitNumber;
    private int articleAcceptNumber;
    private int articleNotAcceptNumber;
    private int articleMoney;


}
