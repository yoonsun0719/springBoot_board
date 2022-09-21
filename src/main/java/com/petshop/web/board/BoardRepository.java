package com.petshop.web.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	
	List<BoardEntity> findByBoardWriterContainingAndBoardDelYNIgnoreCase(String board_writer, String  boardDelYN);
	List<BoardEntity> findByBoardTitleContainingAndBoardDelYNIgnoreCase(String board_title,String  boardDelYN);
	List<BoardEntity> findByBoardContentContainingAndBoardDelYNIgnoreCase(String board_conten,String  boardDelYN);
	
	List<BoardEntity> findAllByboardId (int boardId);
	List<BoardEntity> findAllByboardDelYN (String  boardDelYN);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE board_tbl b SET b.board_del_yn = 'Y' WHERE b.board_id = :boardId", nativeQuery=true)
	void delUpdate(@Param("boardId") int boardId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE board_tbl b SET b.board_see = b.board_see + 1 where b.board_id = :boardId", nativeQuery = true)
	int updateView(@Param("boardId") int boardId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE board_tbl b SET b.board_title = :boardTitle, b.board_content = :boardContent WHERE b.board_id = :boardId", nativeQuery = true)
	void boardUpdate(@Param("boardTitle") String boardTitle, @Param("boardContent") String boardContent, @Param("boardId") int boardId);
	
	List<BoardEntity> findByBoardWriterContainingIgnoreCase(String board_writer);
//	List<BoardEntity> findByBoardTitleContainingIgnoreCase(String board_title);
//	List<BoardEntity> findByBoardContentContainingIgnoreCase(String board_content);
	
}