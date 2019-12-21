package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: contribution-system *
 * @classname: PaperDao *
 * @author: lnback *
 * @create: 2019-12-21 16:27
 **/

public interface PaperDao extends JpaRepository<Paper,Integer> {
}
