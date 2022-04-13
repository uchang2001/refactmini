package com.one.miniproject.repository;

import com.one.miniproject.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    Optional<Good> findByPostidAndAndUsername(Long postid, String username);
    List<Good> findAllByPostid(long pid);
    List<Good> findAllByPostidAndUsername(long pid,String name);

    void deleteAllByPostid(Long postid);
}