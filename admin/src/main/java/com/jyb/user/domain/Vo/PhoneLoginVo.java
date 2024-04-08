package com.jyb.user.domain.Vo;

import com.jyb.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneLoginVo {
	private String user_phone;
	private String code;
	private String s;
	private List<User> userList;
}
