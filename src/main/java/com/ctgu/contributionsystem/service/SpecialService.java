package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialService {

    Specialist findByUserId(Integer userid);

    Page<Paper> findAll(Pageable pageable);
}
