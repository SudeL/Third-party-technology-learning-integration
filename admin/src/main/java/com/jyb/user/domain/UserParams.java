package com.jyb.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * ClassName:Params
 * Package:com.jyb.group.domain
 * Description:
 *
 * @Author 陈进飞
 * @Create 2024/4/6 23:18
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParams {
    private String user_id;
    private String user_name;
    private String user_group_id;
    private String user_phone;
    private Integer pageNum;
    private Integer pageSize;
}
