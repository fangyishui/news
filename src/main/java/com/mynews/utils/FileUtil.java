package com.mynews.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static final String FILE_PATH = "D:\\tts\\";
//	private static final String FILE_PATH = "/tts/";

	//文件上传工具类服务方法
    public static String uploadFile(MultipartFile file, String fileName,HttpServletRequest request){

    	String suffixName = fileName.substring(fileName.lastIndexOf("."));

    	String name = UUID.randomUUID()+"_"+System.currentTimeMillis() + suffixName;

        File path = new File(FILE_PATH);

        if(!path.exists()){
        	path.mkdirs();
        }

    	File newFile = new File(FILE_PATH+name);

        try {
			file.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

        return name;
    }

    //文件类型
    public static Boolean fileSuffix(String fileName) {

    	String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
    	String type = fileType.toUpperCase();

    	if(type.equals("PNG") || type.equals("TXT") || type.equals("JPG") || type.equals("JPEG")) {

    		return true;
    	}

    	return false;
    }
}
