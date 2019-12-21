package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @program: contribution-system *
 * @classname: PaperTag *
 * @author: lnback *
 * @create: 2019-12-21 13:02
 **/
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "paper_tag")
public class PaperTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paperTagId;
    @Column(name = "paper_id")
    private Integer paperId;
    @Column(name = "tag_id")
    private Integer tagId;
}
