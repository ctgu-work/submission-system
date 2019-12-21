package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

@Transactional
public interface AdminDao extends JpaRepository<Integer,Integer> {

//    @RestResource(exported = false)
//    @Query(value = "",nativeQuery=true)
//    List findCount();
}
