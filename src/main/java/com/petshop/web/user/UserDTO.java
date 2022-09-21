package com.petshop.web.user;

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
public class UserDTO {
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
}
