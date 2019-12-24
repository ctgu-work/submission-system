package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.dto.PaperVo;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.PaperCategory;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialService {

    Specialist findByUserId(Integer userid);
    List<Paper> findAll(Integer Category);

    ReviewPaper addReviewPaper1(ReviewPaper reviewPaper);
    Specialist addSpecialist(Specialist specialist);
    Page<Paper> findAll(Pageable pageable);
    String findNameBySpecialistId(@Param("specialistId") Integer specialistId);

    Integer Updatestatus(@Param("paperId") Integer paperId,@Param("status") Integer status);

    PaperVo findByPaperId(@Param("paperId") Integer paperId);

    List<Paper> findAllById(@Param("specialistId") Integer specialistId);

    List<Specialist> findAll();

}
