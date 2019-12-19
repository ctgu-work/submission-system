package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


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
@Table(name = "paper")
public class Paper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paperId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;
    @Column(name = "category")
    private Integer category;
    @Column(name = "tag")
    private String tag;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "submit_date")
    private Timestamp submitDate;
    @Column(name = "specialist_id")
    private Integer specialistId;
    @Column(name = "check_date")
    private Timestamp checkDate;
    @Column(name = "click_rate")
    private Integer clickRate;
    @Column(name = "status")
    private Integer status;
}
