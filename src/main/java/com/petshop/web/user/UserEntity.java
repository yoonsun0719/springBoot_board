package com.petshop.web.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.petshop.web.board.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_tbl")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private int user_id;
	private String user_nickname;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_addr_1;	
	private String user_addr_2;	
	private String user_tel;	
	private String user_del_yn;	
	private String user_del_date;
	private String user_reg_date;
	private String user_pet_type;
	private String oauth;
	//kakao, google
//	@OneToMany(mappedBy = "userEntity")
//	private List<BoardEntity> boardList;
}
