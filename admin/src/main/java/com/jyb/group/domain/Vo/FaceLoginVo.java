package com.jyb.group.domain.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaceLoginVo {
	private String base64;
	private String group_id;
}
