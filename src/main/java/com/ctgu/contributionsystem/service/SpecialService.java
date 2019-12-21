package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialService {

    Specialist findByUserId(Integer userid);
    Specialist addSpecialist(Specialist specialist);
    Page<Paper> findAll(Pageable pageable);
}
