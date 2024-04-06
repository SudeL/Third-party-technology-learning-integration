package com.jyb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
	private static final String SUCCESS = "0";
	private static final String ERROR = "-1";

//	调用成功或识别，0成功，-1失败
	private String code;
//	如果报错，报错信息为msg
	private String msg;
//	返回的信息
	private Object data;
//定义返回调用成功状态的方法（不含数据）
	public static Result success(){
		Result result = new Result();
		result.setCode(SUCCESS);
		return result;
	}

	//定义返回调用成功状态的方法（含数据）
	public static Result success(Object object){
		Result result = new Result();
		result.setCode(SUCCESS);
		result.setData(object);
		return result;
	}

//	定义错误方法，返回调用失败状态和报错信息
	public static Result error(String msg){
		Result result = new Result();
		result.setCode(ERROR);
		result.setMsg(msg);
		return result;
	}


}
