package com.jyb.group.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class GroupParams {
    private String group_id;
    private Integer pageNum;
    private Integer pageSize;
}
