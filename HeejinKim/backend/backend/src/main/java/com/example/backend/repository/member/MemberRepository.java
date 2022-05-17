package com.example.backend.repository.member;

import com.example.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Transactional
    @Query(value = "select * from member where member_no in (select member_member_no from member_auth where auth = :auth)", nativeQuery = true)
    public List<Member> selectMemberWithRole(String auth);

    @Transactional
    @Query(value = "select * from member where user_id = :userId" , nativeQuery = true)
    Optional<Member> findByUserId(String userId);  //들어온 Id가 DB에 있는지 찾기


    @Transactional
    @Query(value = "select * from member  where email = :email",nativeQuery = true)
    Optional<Member> findByUserEmail(String email);




    @Transactional
    @Modifying
    @Query(value ="update Member n set n.password = :password where n.user_id = :userId ",nativeQuery = true)
    void modifyNoLoginPw(String userId,String password);








}
