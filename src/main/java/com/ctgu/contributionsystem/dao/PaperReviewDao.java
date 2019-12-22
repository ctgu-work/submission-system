package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PaperReviewDao extends JpaRepository<ReviewPaper, Integer> {

}
