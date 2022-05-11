package com.example.backend.repository.Board;

import com.example.backend.entity.Board;
import com.example.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface BoardRepository  extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyWord);

    @Transactional
    @Query(value = "select * from board where writer = :searchId", nativeQuery = true)
    public List<Board> findIdList(String searchId);
}

