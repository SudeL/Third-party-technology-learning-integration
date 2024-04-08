package com.jyb.files;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.jyb.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/*
* 文件上传下载类处理
* */
@RestController
@CrossOrigin
@RequestMapping("/files")
public class Files {
//	文件上传路径
	private static  final  String filePath = System.getProperty("user.dir") + "/file/";
	/*
	* 文件上传
	* */
	@PostMapping("/upload")
	public Result upload(MultipartFile file){
		synchronized (Files.class){
//			获取时间戳
			String flag = System.currentTimeMillis() + "";
//			原始文件名
			String fileName = file.getOriginalFilename();
			try {
//				没有文件夹会创建
				if (!FileUtil.isDirectory(filePath)){
					FileUtil.mkdir(filePath);
				}
				//文件存储格式：时间戳+文件名
				FileUtil.writeBytes(file.getBytes(),filePath + flag + "~" + fileName);
				System.out.println(fileName + "上传成功");
				Thread.sleep(1L);
			}catch (Exception e){
				System.out.println("文件上传失败");
				System.out.println(e);
			}
			return  Result.success(flag + "~" + fileName);
		}
	}

	/*
	* 文件下载
	* */

	@GetMapping("/{flag}")
	public void avatarPath(@PathVariable String flag, HttpServletResponse response){
		System.out.println(flag);
	if(!FileUtil.isDirectory(filePath)){
		FileUtil.mkdir(filePath);
	}
		OutputStream os;
		List<String> fileName = FileUtil.listFileNames(filePath);
		String avatar = fileName.stream().filter(name -> name.contains(flag)).findAny().orElse("");
		try {
			if (StrUtil.isNotEmpty(avatar)){
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
				response.setContentType("application/octet-stream");
				byte[] bytes = FileUtil.readBytes(filePath +avatar);
				os = response.getOutputStream();
				os.write(bytes);
				os.flush();
				os.close();
			}
		}catch (Exception e){
			System.out.println("文件下载失败");
		}
	}


}
