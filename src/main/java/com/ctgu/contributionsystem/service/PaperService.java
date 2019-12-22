package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;

/**
 * @program: contribution-system *
 * @classname: PaperService *
 * @author: lnback *
 * @create: 2019-12-21 15:59
 **/

public interface PaperService {
    /**
     * 增加新文章
     * @param paper
     * @return
     */
    int addPaper(Paper paper);
}
