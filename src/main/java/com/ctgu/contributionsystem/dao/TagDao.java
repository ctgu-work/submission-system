package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: contribution-system *
 * @classname: TagDao *
 * @author: lnback *
 * @create: 2019-12-21 18:21
 **/
@Transactional
public interface TagDao extends JpaRepository <Tag,Integer>{

    @Query(nativeQuery = true,value = "SELECT a.tag_id , a.tag_detail from tag a , paper_tag b where a.tag_id = b.tag_id and b.paper_id = :PaperId")
    List<Tag> findByPaperId(@Param("PaperId")Integer PaperId);
    /**
     * 通过标签内容查询
     * @param tagDetail
     * @return
     */
    Tag findBytagDetail(String tagDetail);

}
