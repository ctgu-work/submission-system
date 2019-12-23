package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : kun
 * @date ： 2019/12/22
 * @description ：this is a code
 **/

@Getter
@Setter
public class SpecialCount{

    private Integer userId;
    private String name;
    private Long count;

    public SpecialCount() {
        super();
        this.userId = userId;
        this.name = name;
        this.count = count;
    }
}
