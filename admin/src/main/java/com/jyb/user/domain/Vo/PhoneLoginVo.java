package com.jyb.user.domain.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneLoginVo {
	private String user_phone;
	private String code;
	private String s;
}
