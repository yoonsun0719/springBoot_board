package com.petshop.web.user;

import lombok.Data;

//@Generated("jsonschema2pojo")

//com.petshop.web.user.KakaoProfile.java
@Data
public class KakaoProfile {
	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	// com.petshop.web.user.Properties.java
	@Data
	public class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}

	// com.petshop.web.user.KakaoAccount.java-
	@Data
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		public Boolean has_birthday;
		public Boolean birthday_needs_agreement;
		public String birthday;
		public String birthday_type;
		public Boolean has_gender;
		public Boolean gender_needs_agreement;
		public String gender;

		// com.petshop.web.user.Profile.java
		@Data
		public class Profile {
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
		}
	}
}
