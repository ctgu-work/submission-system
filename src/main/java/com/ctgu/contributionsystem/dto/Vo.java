package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : kun
 * @date ： 2019/12/23
 * @description ：this is a code
 **/

@Getter
@Setter
public class Vo {

    private Integer specialistId;
    private String name;
    private Integer category;
    private String phoneNumber;
    private Integer userId;
    private Integer count;
    private Integer status;

    public Vo() {
        this.specialistId = specialistId;
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.count = count;
        this.status = status;
    }
}
