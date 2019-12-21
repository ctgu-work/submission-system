package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.PaperCategory;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecialService {

    Specialist findByUserId(Integer userid);

    List<Paper> findAll(Integer category);

    ReviewPaper addReviewPaper1(ReviewPaper reviewPaper);
}
