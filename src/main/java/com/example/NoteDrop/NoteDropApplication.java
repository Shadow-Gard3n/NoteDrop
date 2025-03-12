package com.example.NoteDrop;

import com.example.NoteDrop.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class )
public class NoteDropApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteDropApplication.class, args);
	}

}
