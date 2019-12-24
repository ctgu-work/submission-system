package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminService {


    List<Specialist> findAllByStatus();

    Integer updateSpecialitProhibit(@Param("specialistId") Integer specialistId);

    Admin findByPhoneNumber(String phoneNumber);

    Integer updateSpecialitProhibit1(@Param("specialistId") Integer specialistId);

    void delete(Integer specialistId);

    Integer Updatestatus(@Param("specialistId") Integer specialistId,@Param("status") Integer status);

    Integer UpdateCategory(@Param("specialistId") Integer specialistId,@Param("category") Integer category);

    Specialist findSpecialistById(@Param("specialistId") Integer specialistId);

    Integer UpdateUser(@Param("userId") Integer userId,@Param("name1") String name,@Param("nickName") String nickName,@Param("phoneNumber") String phoneNumber);
}
