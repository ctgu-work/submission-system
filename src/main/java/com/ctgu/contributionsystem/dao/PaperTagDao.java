package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.PaperTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: contribution-system *
 * @classname: PaperTagDao *
 * @author: lnback *
 * @create: 2019-12-21 21:12
 **/

public interface PaperTagDao extends JpaRepository<PaperTag,Integer> {

}
