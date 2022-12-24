package com.mumu.Online.Exam.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OnlineExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamSystemApplication.class, args);
	}

}
