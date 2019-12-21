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
@Table(name = "paper_status")
public class PaperStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;
    @Column(name = "status_detail")
    private String statusDetail;
}
