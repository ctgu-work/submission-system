package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:59
 * @ClassName PaperService
 * @Version 1.0.0
 */
public interface PaperService {
    Page<Paper> findAllByUserId(Pageable pageable, @Param("userId") Integer userId);

}