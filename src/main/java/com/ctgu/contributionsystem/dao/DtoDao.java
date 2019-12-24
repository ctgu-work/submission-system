package com.ctgu.contributionsystem.dao;
//
//import com.ctgu.contributionsystem.dto.SpecialCount;
//import com.ctgu.contributionsystem.model.Paper;
//import com.ctgu.contributionsystem.model.Specialist;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface DtoDao extends JpaRepository<Paper, Long> {
//
//    @Query(value = "SELECT c.user_id as userId,d.name,d.nick_name,d.id_card,d.phone_number,d.pass_word,d.email" +
//            ",COUNT(*) as count,d.money FROM paper c,user d where c.user_id = d.user_id GROUP BY c.user_id",nativeQuery = true)
//    List findCount();
//}
