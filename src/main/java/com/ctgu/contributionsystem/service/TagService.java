package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Tag;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @program: contribution-system *
 * @classname: TagService *
 * @author: lnback *
 * @create: 2019-12-21 18:26
 **/

public interface TagService {

    List<Tag> findByPaperId(@Param("PaperId")Integer PaperId);

    /**
     * 返回TagID
     * @param tag
     * @return
     */
    int getTagId(String tag);

}
