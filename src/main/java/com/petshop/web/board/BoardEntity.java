package com.petshop.web.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "board_tbl")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class BoardEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	@Column(name = "board_id")
	private int boardId;
	private int b_user_id;
	@Column(name = "board_writer")
	private String boardWriter;
	@Column(name = "board_title")
	private String boardTitle;
	@Column(name = "board_content")
	private String boardContent;
	private String board_reg_date;
	@ColumnDefault("0")
	private String board_see;
	@Column(name="board_del_yn")
	private String boardDelYN;
	

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private UserEntity userEntity;

}
