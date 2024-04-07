package com.jyb.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_info")
public class User {
//	主键id
	@Id
//	策略
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "user_password")
	private String user_password;

	@Column(name = "user_group_id")
	private String user_group_id;

	@Column(name = "user_face_path")
	private String user_face_path;

	@Column(name = "user_isadmin")
	private String user_isadmin;

	@Column(name = "user_phone")
	private String user_phone;
}
