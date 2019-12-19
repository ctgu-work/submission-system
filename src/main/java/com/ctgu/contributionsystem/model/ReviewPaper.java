package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "review_paper")
public class ReviewPaper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewPaperId;
    @Column(name = "specialist_id")
    private Integer specialistId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "comment")
    private String comment;
    @Column(name = "review_time")
    private Timestamp reviewTime;
    @Column(name = "paper_id")
    private Integer paperId;
}
