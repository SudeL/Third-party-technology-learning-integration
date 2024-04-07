package com.jyb.util;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class FaceUtil {



	static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();


	//人脸识别密钥

	//查询分组
	public static String queryGroup() {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("查询分组失败：" + e);
			return null;
		}
	}

	//获取用户列表
	public static String getUserList(String group_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("获取用户列表失败：" + e);
			return null;
		}
	}

	//创建用户组
	public static String addUserList(String group_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("创建用户组失败：" + e);
			return null;
		}
	}

	//删除用户组
	public static String deleteUserList(String group_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("删除用户组失败：" + e);
			return null;
		}
	}

	//人脸1:N对比
	public static String compareFace(String group_id_list, String base64) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			// image 可以通过 getFileContentAsBase64("path") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
			RequestBody body = RequestBody.create(mediaType, "{\"group_id_list\":\"" + group_id_list + "\",\"image\":\"" + base64 + "\",\"image_type\":\"BASE64\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("人脸验证失败：" + e);
			return null;
		}
	}

	/**
	 * 获取文件base64编码
	 *
	 * @param path 文件路径
	 * @return base64编码信息，不带文件头
	 * @throws IOException IO异常
	 */
	static String getFileContentAsBase64(String path) throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(path));
		String base64 = Base64.getEncoder().encodeToString(b);
		return base64;
	}

	//添加人脸
	public static String addFace(String group_id, String path, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");    // {"aa" : "aa" , "aaxx" : "xx"}
			// image 可以通过 getFileContentAsBase64("path") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
			String base64 = getFileContentAsBase64(path);
			System.out.println(base64.length());
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\",\"image\":\"" + base64 + "\",\"image_type\":\"BASE64\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("添加人脸失败" + e);
			return null;
		}
	}

	//更新人脸
	public static String updateFace(String group_id, String path, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");    // {"aa" : "aa" , "aaxx" : "xx"}
			// image 可以通过 getFileContentAsBase64("path") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
			String base64 = getFileContentAsBase64(path);
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\",\"image\":\"" + base64 + "\",\"image_type\":\"BASE64\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("更新人脸失败：" + e);
			return null;
		}
	}

	//人脸对比v3（图片）
	public static String matchFace(String path1, String path2) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			String base64A = getFileContentAsBase64(path1);
			String base64B = getFileContentAsBase64(path2);
			RequestBody body = RequestBody.create(mediaType, "[{\"image\":\"" + base64A + "\",\"image_type\":\"BASE64\"},{\"image\":\"" + base64B + "\",\"image_type\":\"BASE64\"}]");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/match?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("人脸对比失败：" + e);
			return null;
		}
	}

	//查询用户信息
	public static String queryUser(String group_id, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("查询用户信息失败：" + e);
			return null;
		}
	}

	//删除用户信息
	public static String deleteUser(String group_id, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("删除用户信息失败：" + e);
			return null;
		}
	}

	//人脸检测
	// 可以获取face_token 和 log_id
	public static String detectFace(String path, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");    // {"aa" : "aa" , "aaxx" : "xx"}
			// image 可以通过 getFileContentAsBase64("path") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
			String base64 = getFileContentAsBase64(path);
			RequestBody body = RequestBody.create(mediaType, "{\"image\":\"" + base64 + "\",\"image_type\":\"BASE64\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("人脸检测失败：" + e);
			return null;
		}
	}

	/**
	 * 人脸删除
	 *
	 * @param face_token 表示需要删除的人脸图片token，（由数字、字母、下划线组成）长度限制64B
	 * @param group_id
	 * @param log_id     表示请求标识码，随机数，唯一
	 * @param user_id
	 * @return
	 */
	public static String deleteFace(String face_token, String group_id, String log_id, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"face_token\":\"" + face_token + "\",\"group_id\":\"" + group_id + "\",\"log_id\":" + log_id + "\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("人脸删除失败：" + e);
			return null;
		}
	}

	//获取用户人脸列表
	public static String faceList(String group_id, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"group_id\":\"" + group_id + "\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("获取用户人脸列表失败：" + e);
			return null;
		}
	}

	/**
	 * 复制用户
	 *
	 * @param dst_group_id 表示复制到哪个组的组id
	 * @param src_group_id 表示从哪个组复制的组id
	 * @param user_id      表示用户id
	 * @return
	 */
	public static String copyUser(String dst_group_id, String src_group_id, String user_id) {
		try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"dst_group_id\":\"" + dst_group_id + "\",\"src_group_id\":\"" + src_group_id + "\",\"user_id\":\"" + user_id + "\"}");
			Request request = new Request.Builder()
					.url("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/copy?access_token=" + getAccessToken())
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			Response response = HTTP_CLIENT.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			System.out.println("复制用户失败：" + e);
			return null;
		}
	}

	/**
	 * 从用户的AK，SK生成鉴权签名（Access Token）
	 *
	 * @return 鉴权签名（Access Token）
	 * @throws IOException IO异常
	 */
	static String getAccessToken() throws IOException {
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
				+ "&client_secret=" + SECRET_KEY);
		Request request = new Request.Builder()
				.url("https://aip.baidubce.com/oauth/2.0/token")
				.method("POST", body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.build();
		Response response = HTTP_CLIENT.newCall(request).execute();
		return new JSONObject(response.body().string()).getString("access_token");
	}

}
