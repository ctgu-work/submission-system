package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: contribution-system *
 * @classname: TagDao *
 * @author: lnback *
 * @create: 2019-12-21 18:21
 **/
@Transactional
public interface TagDao extends JpaRepository <Tag,Integer>{


}
