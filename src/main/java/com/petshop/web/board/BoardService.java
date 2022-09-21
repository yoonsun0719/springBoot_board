package com.petshop.web.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	public BoardEntity insertBoard(BoardDTO boardDTO) {
		BoardEntity boardEntity = BoardEntity
				.builder()
				.boardId(boardDTO.getBoard_id())
				.b_user_id(boardDTO.getB_user_id())
				.boardWriter(boardDTO.getBoard_writer())
				.boardTitle(boardDTO.getBoard_title())
				.boardContent(boardDTO.getBoard_content())
				//.board_reg_date(boardDTO.getBoard_reg_date())
				//.board_see(boardDTO.getBoard_see())
				.build();
		
		return boardRepository.save(boardEntity);
	}
	
	public List<BoardEntity> findAllBoard() {
		return boardRepository.findAll();
	}
	 

	
	public List<BoardEntity> boardSearch(BoardDTO boardDTO) {
		//return boardRepository.findByBoardWriterContainingIgnoreCase(board_writer);
		List<BoardEntity> list = new ArrayList<>();  
		boardDTO.setBoard_content(boardDTO.getSearchData());
		boardDTO.setBoard_title(boardDTO.getSearchData());
		boardDTO.setBoard_writer(boardDTO.getSearchData());
		
		if(boardDTO.getType().equals("1")) {
			list = boardRepository.findByBoardWriterContainingAndBoardDelYNIgnoreCase(boardDTO.getBoard_writer(), "N");
			System.out.println("1번"+list);
		}
		if(boardDTO.getType().equals("2")) {
			list = boardRepository.findByBoardTitleContainingAndBoardDelYNIgnoreCase(boardDTO.getBoard_title(), "N");
			System.out.println("2번"+list);
		}
		if(boardDTO.getType().equals("3")) {
			list = boardRepository.findByBoardContentContainingAndBoardDelYNIgnoreCase(boardDTO.getBoard_content(), "N");
			System.out.println("3z번"+list);
		}
		System.out.println("보드디티오입니다........."+list);
		return list;
	}
	
	public List<BoardEntity> boardRead(int boardId) {
		System.out.println("보드리드"+boardRepository.findAllByboardId(boardId));
		return boardRepository.findAllByboardId(boardId);
	}
	
	//수정
	public void boardUpdate(BoardDTO boardDTO) {
		
		System.out.println("서비스업데이트11111 = " + boardDTO);
		
//		BoardEntity boardEntity = BoardEntity
//				.builder()
//				.boardWriter(boardDTO.getBoard_writer())
//				.boardTitle(boardDTO.getBoard_title())
//				.boardContent(boardDTO.getBoard_content())
//				.build();
				 
		boardRepository.boardUpdate(boardDTO.getBoard_title(), boardDTO.getBoard_content(), boardDTO.getBoard_id());
		
		//System.out.println("서비스업데이트22222 = " + boardRepository.boardUpdate(boardDTO.getBoard_title(), boardDTO.getBoard_content(), boardDTO.getBoard_id()));
		//System.out.println("서비스업데이트33333 = " + boardEntity);		
				
//		return boardEntity;
		
		
		
		
//		List<BoardEntity> list = new ArrayList<>();
//		
//		if(boardDTO.getType() == "1") {
//			list = boardRepository.findByBoardWriterContainingIgnoreCase(boardDTO.getBoard_writer());
//		}
//		if(boardDTO.getType() == "2") {
//			list = boardRepository.findByBoardWriterContainingIgnoreCase(boardDTO.getBoard_title());
//		}
//		if(boardDTO.getType() == "3") {
//			list = boardRepository.findByBoardWriterContainingIgnoreCase(boardDTO.getBoard_content());
//		}
//		return boardEntity;
	}
	

	// N -> Y
	public void delUpdate(int boardId) {
		boardRepository.delUpdate(boardId);
	}
	
	@Transactional
	public int updateView(int boardId) {
		return boardRepository.updateView(boardId);
	}
}
