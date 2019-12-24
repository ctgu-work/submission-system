package com.ctgu.contributionsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

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
    private String nickName;
    private String idCard;
    private String phoneNumber;
    private String passWord;
    private String email;
    private Integer count;
    private Integer money;

    public SpecialCount() {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.email = email;
        this.count = count;
        this.money = money;
    }
}
