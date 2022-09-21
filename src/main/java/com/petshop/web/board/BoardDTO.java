package com.petshop.web.board;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class BoardDTO {
	private int board_id;
	private int b_user_id;
	private String board_writer;
	private String board_title;
	private String board_content;
	private String board_reg_date;
	private String board_see;	
	private String type;
	private String searchData;
	@Column(name="board_del_yn")
	private String boardDelYN;
}
