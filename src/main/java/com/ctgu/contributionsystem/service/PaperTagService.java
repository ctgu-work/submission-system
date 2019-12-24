package com.ctgu.contributionsystem.service;

/**
 * @program: contribution-system *
 * @classname: PaperTagService *
 * @author: lnback *
 * @create: 2019-12-21 20:27
 **/

public interface PaperTagService {
    /**
     * 添加paperTag到数据库中
     * @param paperId
     * @param tagId
     * @return
     */
    int addPaperTag(int paperId, int tagId);

    /**
     * 删除关联表中的数据
     * @param paperId
     */
    void deleteAllPaperTagByPaperId(int paperId);
}
