package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:12
 * @ClassName User
 * @Version 1.0.0
 */
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "id_card")
    private String idCard;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "money")
    private Integer money;
    @Column(name = "description")
    private String description;
}
