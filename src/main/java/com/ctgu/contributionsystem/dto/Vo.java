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
    private Long count;

    public Vo() {
        this.specialistId = specialistId;
        this.name = name;
        this.count = count;
    }
}
