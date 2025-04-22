package com.example.NoteDrop;
//import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication   // for testing -->(exclude = SecurityAutoConfiguration.class )
public class NoteDropApplication {
//	Dotenv dotenv = Dotenv.load();
//	System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
//	System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
//	System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
//	System.setProperty("SUPABASE_URL", dotenv.get("SUPABASE_URL"));
//	System.setProperty("SUPABASE_APIKEY", dotenv.get("SUPABASE_APIKEY"));

	public static void main(String[] args) {
		SpringApplication.run(NoteDropApplication.class, args);
	}
}
