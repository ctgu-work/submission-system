package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleService {

    List<Paper> findAll();

    Integer UpdateById(@Param("paperId") Integer paperId);
}
