package com.ctgu.contributionsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "submit_paper")
public class SubmitPaper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submitPaperId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "paper_id")
    private Integer paperId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "status_detail")
    private Timestamp statusDetail;

}
