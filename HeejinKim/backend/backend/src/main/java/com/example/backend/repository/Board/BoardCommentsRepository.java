package com.example.backend.repository.Board;

import com.example.backend.entity.Board;
import com.example.backend.entity.BoardComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardCommentsRepository extends JpaRepository<BoardComments,Long> {

    @Transactional
    @Query("select c from BoardComments c join c.board tb where tb.boardNo = :boardNo")
    List<BoardComments> findAllBoardCommentByBoardId(Long boardNo);

    List<BoardComments>findBoardCommentByBoard(Board board);


    @Transactional
    @Query(value = "select * from board_comments where comment_no = :commentNo",nativeQuery = true)
    List<BoardComments> findCommentByCommentNo (Long commentNo);




}
