package com.vaescode.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaescode.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootBootJpaApplication implements CommandLineRunner{
	
	
	@Autowired
	private IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		uploadFileService.deleteAll();
		
		uploadFileService.init();
		
	}

}
