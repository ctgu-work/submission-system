package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : kun
 * @date ： 2019/12/19
 * @description ：this is a code
 **/

@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "admin")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "pass_word")
    private String passWord;
}
