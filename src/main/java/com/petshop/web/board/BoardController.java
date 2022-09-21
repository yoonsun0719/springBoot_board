package com.petshop.web.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "board{pageName}")
	public ModelAndView indexHome(String pageName) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/board" + pageName);
		return mv;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	//목록출력
	@RequestMapping(value= "boardList", method= {RequestMethod.GET})
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		List<BoardEntity> boardList = boardService.findAllBoard();

		mv.addObject("boardList", boardList);
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
	
	/**
	 * 
	 * @param boardDTO
	 * @param request
	 * @param response
	 * @return
	 */
	//검색
	@RequestMapping(value = "boardSearch", method = {RequestMethod.POST})
	public List<BoardEntity> boardSearch(@RequestBody BoardDTO boardDTO, HttpServletRequest request, HttpServletResponse response) {
		List<BoardEntity> boardList = boardService.boardSearch(boardDTO);
		//ModelAndView mv = new ModelAndView();
		//mv.addObject("boardList", boardList);
		//mv.setViewName("board/boardSearch");
		return boardList;
	}
	
	/**
	 * 
	 * @param boardDTO
	 * @param session
	 * @return
	 */
	//등록
	@RequestMapping(value = "boardInsert", method = {RequestMethod.POST})
	public ModelAndView insertBoard(@RequestBody BoardDTO boardDTO, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		boardDTO.setBoard_writer((String) session.getAttribute("user_nickname"));
		boardDTO.setB_user_id((Integer) session.getAttribute("user_id"));
		
		System.out.println("작성자" + "//" + boardDTO.getBoard_writer());
		System.out.println("제목" + "//" + boardDTO.getBoard_title());
		System.out.println("내용" + "//" + boardDTO.getBoard_content());
		
		boardService.insertBoard(boardDTO);
		
		mv.setViewName("board/boardInsert");
		return mv;
	}
	
	/**
	 * 
	 * @param boardId
	 * @param edit
	 * @param request
	 * @param response
	 * @return
	 */
	//읽기
	@RequestMapping(value= "boardRead", method= {RequestMethod.GET})
	public ModelAndView boardRead(@RequestParam(value =  "boardId") int boardId, @RequestParam(value = "edit") int edit, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();	
		List<BoardEntity> boardRead = boardService.boardRead(boardId);
		
		boardService.updateView(boardId); // view counting++
		
		mv.addObject("boardRead", boardRead);
		if(edit == 1) {
			mv.setViewName("board/boardEdit");
		}else {
			mv.setViewName("board/boardRead");			
		}
		return mv;
	}
	
	/**
	 * 
	 * @param boardDTO
	 */
	//N->Y로 변경
	@RequestMapping(value = "boardDel", method = {RequestMethod.POST})
	public void boardDel(@RequestBody BoardDTO boardDTO) {
		//System.out.println("@@@@@@@@@@@@@"+boardDTO.getBoard_id());
		boardService.delUpdate(boardDTO.getBoard_id());
	}
	
	
	
	//수정기능   DATE {d 'yyyy-mm-dd'}   DATE_FORMAT(now(), '%Y-%m-%d')
	/**
	 * 
	 * @param boardDTO
	 */
	@RequestMapping(value = "boardUpdate", method = {RequestMethod.POST})
	public void boardUpdate(@RequestBody BoardDTO boardDTO) {
		//ModelAndView mv = new ModelAndView();
		//BoardEntity boardUpdate = boardService.boardUpdate(boardDTO);	
		System.out.println("컨트롤러업데이트 1 = " + boardDTO);
		
		boardService.boardUpdate(boardDTO);
	//	System.out.println("컨트롤러업데이트 2 = " + boardService.boardUpdate(boardDTO));
		
	}
	
	
//	//Edit
//	@RequestMapping(value = "boardEdit", method = {RequestMethod.POST})
//	public ModelAndView boardEdit(@RequestBody BoardDTO boardDTO) {
//		ModelAndView mv = new ModelAndView();
//		//BoardEntity boardUpdate = boardService.boardUpdate(boardDTO);	
//		mv.setViewName("board/boardEdit");
//		
//		//System.out.println("컨트롤러" + boardDTO);
//		return mv;
//	}
	
}
