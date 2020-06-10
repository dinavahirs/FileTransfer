package com.assignment.transfer.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {

	/*
	 * It is preferable to create a public static variable in a common controller,
	 * as the file will be downloaded from the same place where it was originally
	 * uploaded.
	 */

	private static String downloadDirectory = "C://test//";

	@RequestMapping("/download/{fileName:.+}")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File(downloadDirectory + fileName);
		if (file.exists()) {

			// get the mimetype, to verify if the file extension is of known mime type
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());

			// if mimetype is unknown, set it to application/octet-stream
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			// To download file as attachment
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			// To view downloaded file within browser
//			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}

	}

}
