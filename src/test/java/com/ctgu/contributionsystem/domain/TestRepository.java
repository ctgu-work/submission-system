package com.ctgu.contributionsystem.domain;

import com.ctgu.contributionsystem.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TestRepository
 *
 * @author chase
 * @date 2019/10/14 0014
 */
public interface TestRepository extends JpaRepository<Demo, Long> {
}
