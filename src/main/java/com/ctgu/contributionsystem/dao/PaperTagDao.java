package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.PaperTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: contribution-system *
 * @classname: PaperTagDao *
 * @author: lnback *
 * @create: 2019-12-21 21:12
 **/

public interface PaperTagDao extends JpaRepository<PaperTag,Integer> {

    /**
     * 通过paperId删除关联变中的数据
     * @param paperId
     */
    @Transactional
    void deleteByPaperId(@Param("paperId") Integer paperId);
}
