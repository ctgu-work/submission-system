package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:23
 * @ClassName UserDao
 * @Version 1.0.0
 */
@Transactional
public interface UserDao extends JpaRepository<User, Integer>{
    User findByPhoneNumber(String PhoneNumber);
//    @Modifying
//    @Query(nativeQuery = true,value = "update user a set a.avatar_url = :avatarUrl where a.phone_number = :phoneNumber")
//    int modifyAvatarUrlByphoneNumber(@Param("avatarUrl") String avatarUrl, @Param("phoneNumber") String phoneNumber);
}
