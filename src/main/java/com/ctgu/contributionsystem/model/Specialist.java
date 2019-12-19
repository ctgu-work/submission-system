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
@Table(name = "specialist")
public class Specialist implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer specialistId;
    @Column(name = "name")
    private String name;
    @Column(name = "id_card")
    private String idCard;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "status")
    private Integer status;
    @Column(name = "category")
    private Integer category;
    @Column(name = "pass_word")
    private String passWord;
}
