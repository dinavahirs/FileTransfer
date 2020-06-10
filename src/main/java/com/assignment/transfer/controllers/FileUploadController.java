package com.assignment.transfer.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class FileUploadController implements ErrorController {

	// Below variable can be placed at application.properties and retrieved
	private static String uploadDirectory = "C://test//";

	@RequestMapping("/")
	public ModelAndView showUpload() {
		return new ModelAndView("upload");
	}

//	@PostMapping("/upload")
	@RequestMapping(value = "/uploadmultiple", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ModelAndView multiFileUpload(@RequestParam("files") MultipartFile files,
			RedirectAttributes redirectAttributes) {

		if (!files.isEmpty()) {

			if (files.getSize() == 1) {
				singlefileUpload((MultipartFile) files.getResource());
			} else {
				Arrays.asList(files).stream().forEach(file -> fileUpload(file));
//				Arrays.asList(files).stream()
//									.map(file -> fileUpload(file, redirectAttributes));
//									.collect(Collectors.toList());
			}

		} else {
			return new ModelAndView("status", "message", "Please select atleast single file and try again");
		}

		return new ModelAndView("status", "message", "File(s) Uploaded sucessfully");
	}

	@PostMapping("/upload")
	public ModelAndView singlefileUpload(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}

		try {
			// read and write the file
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status", "message", "File Uploaded sucessfully");
	}

	private void fileUpload(@RequestParam("file") MultipartFile file) {

		try {
			// read and write the file
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// This code will be useful when error jsp is created under WEB-INF, instead of
	// displaying content in the html under templates folder
//	@RequestMapping("/error")
//	public ModelAndView handleError(ModelAndView mav) {
//		mav.setViewName("error");
//		return mav;
//		
//	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		return String.format(
				"<html><body><h2>Default Error Page - Excluding whitelabel error</h2><div>Status code: <b>%s</b></div>"
						+ "<div>Exception Message: <b>%s</b></div><body></html>",
				statusCode, exception == null ? "N/A" : exception.getMessage());
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
