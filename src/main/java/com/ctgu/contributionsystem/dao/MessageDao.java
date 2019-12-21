package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @program: contribution-system *
 * @classname: MessageDao *
 * @author: lnback *
 * @create: 2019-12-21 12:23
 **/

@Transactional
public interface MessageDao extends JpaRepository<Message,Integer> {

}
