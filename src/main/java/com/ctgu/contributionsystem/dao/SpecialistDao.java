package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/


@Transactional
public interface SpecialistDao extends JpaRepository<Specialist, Integer>{

    //通过user查找专家

    @Query(value = "select * from specialist where user_id = :userId",nativeQuery = true)
    Specialist findByuserId(@Param("userId") Integer userId);



    @Query(nativeQuery = true,value = "select user.name from user , specialist where user.user_id = specialist.user_id  and specialist.specialist_id = :specialistId")
    String findNameBySpecialistId(@Param("specialistId") Integer specialistId);

    //全部专家

    @Query(value = "select * from specialist where status = 1",nativeQuery = true)
    List<Specialist> findAllByStatus();

    //禁用

    @Modifying
    @Query(value = "UPDATE specialist SET specialist.prohibit = 2 WHERE specialist_id = :specialistId",nativeQuery = true)
    Integer UpdateSpecialit(@Param("specialistId") Integer specialistId);

    //取消禁用

    @Modifying
    @Query(value = "UPDATE specialist SET specialist.prohibit = 1 WHERE specialist_id = :specialistId",nativeQuery = true)
    Integer UpdateSpecialit1(@Param("specialistId") Integer specialistId);

    @Modifying
    @Query(value = "UPDATE specialist SET specialist.status = :status WHERE specialist_id = :specialistId",nativeQuery = true)
    Integer UpdateStatus(@Param("specialistId") Integer specialistId,@Param("status") Integer status);

    @Modifying
    @Query(value = "UPDATE specialist SET specialist.category = :category WHERE specialist_id = :specialistId",nativeQuery = true)
    Integer UpdateCategory(Integer specialistId, Integer category);

    @Query(value = "select * from specialist where specialist_id = :specialistId",nativeQuery = true)
    Specialist findSpecialistById(@Param("specialistId") Integer specialistId);
}
