//package com.jyb.util;
//
//
//import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
//import com.aliyun.tea.TeaException;
//
//public class SMSUtil {
//
//
//	public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
//		com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
////密钥
//
//		config.endpoint = "dysmsapi.aliyuncs.com";
//		return new com.aliyun.dysmsapi20170525.Client(config);
//	}
//
//	public static String sendSMS(String phone, String code) {
//		try {
//			com.aliyun.dysmsapi20170525.Client client = createClient();
//			com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
//					.setSignName("邓同学学习家园")
//					.setTemplateCode("SMS_296920061")
//					.setPhoneNumbers(phone)
//					.setTemplateParam("{\"code\":\"" + code + "\"}");
//			com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
//			// 复制代码运行请自行打印 API 的返回值
//			SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
//			return sendSmsResponse.getBody().getMessage();
//
//		} catch (TeaException error) {
//			// 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
//			// 错误 message
//			System.out.println(error.getMessage());
//			// 诊断地址
//			System.out.println(error.getData().get("Recommend"));
//			com.aliyun.teautil.Common.assertAsString(error.message);
//		} catch (Exception _error) {
//			TeaException error = new TeaException(_error.getMessage(), _error);
//			// 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
//			// 错误 message
//			System.out.println(error.getMessage());
//			// 诊断地址
//			System.out.println(error.getData().get("Recommend"));
//			com.aliyun.teautil.Common.assertAsString(error.message);
//		}
//		return null;
//
//	}
//
//
//	//StringBuffer
//	//编写一个生成6个数字的验证码的方法
//	//注意：是生成6个 0-9的随机数的累加 而非直接生成一个1-999999的随机数
//	public static String getCode() {
//
//		String str = "";
//		for (int i = 0; i < 6; i++) {
//			str = str + (int) Math.floor(Math.random() * 10);
//		}
//		return str;
//
//	}
//
//
//}
