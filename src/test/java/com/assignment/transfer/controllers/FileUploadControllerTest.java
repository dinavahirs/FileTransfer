package com.assignment.transfer.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class FileUploadControllerTest {

	private MockMvc mockMvc;
	
	private  String uploadDirectory = "C://test//";
	@Autowired
	WebApplicationContext webAppContext;
	// mock the beans, if required
	
	
	@Test
	public void showUpload() throws Exception {
		String text ="File content that needs to be uploaded";
        MockMultipartFile file = new MockMultipartFile("file","test.txt", "text/plain", text.getBytes());
        
        mockMvc.perform(MockMvcRequestBuilders.multipart("/Test/")
               .file(file)
               .characterEncoding("UTF-8"))
               .andExpect(MockMvcResultMatchers.status().isOk());

        assertTrue(uploadDirectory.equalsIgnoreCase("C://test//"));
	}


}
