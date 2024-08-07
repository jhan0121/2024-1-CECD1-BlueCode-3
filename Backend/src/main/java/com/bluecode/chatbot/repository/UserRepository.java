package com.bluecode.chatbot.repository;

import com.bluecode.chatbot.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // 유저 테이블 id 기반 user 검색
    Optional<Users> findByUserId(Long userId);

    // 유저 로그인 id 기반 user 검색
    @Query("select u from Users u " +
            "where u.id = :loginId")
    Optional<Users> findByLoginId(@Param("loginId") String loginId);

    @Query("select u from Users u " +
            "where u.username = :username "+
            "and u.email = :email")
    Optional<Users> findByUserNameAndEmail(@Param("username") String username,@Param("email") String email);

    // 유저명, 유저 이메일, 유저 ID 중복 체킹 메소드
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(String id);
}
