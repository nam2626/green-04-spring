package com.spring.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@GetMapping
	public String form() {
		return "upload/form";
	}
	
	@PostMapping("/process")
	public ModelAndView process(MultipartFile file,ModelAndView view) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			view.addObject("message","파일을 선택하세요");
		}else {
			// 업로드한 파일명
			view.addObject("fileName",file.getOriginalFilename());
			view.addObject("fileSize",file.getSize());
			view.addObject("contentType",file.getContentType());
			view.addObject("message","파일 업로드 성공");
			//실제 파일 저장하는 부분
			File root = new File("c:\\fileupload");
			File fpath = new File(root.getAbsoluteFile() + "\\" + file.getOriginalFilename());
			file.transferTo(fpath);
		}
		view.setViewName("upload/result");
		
		return view;
	}

}





