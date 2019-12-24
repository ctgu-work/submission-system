package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.ReviewPaper;
import org.springframework.data.repository.query.Param;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 20:14
 * @ClassName ReviewPaperService
 * @Version 1.0.0
 */
public interface ReviewPaperService {
    ReviewPaper findByPaperId(@Param("paperId")Integer paperId);

    Integer findBySpecailistIdCount(@Param("specialistId") Integer specialistId);
}
