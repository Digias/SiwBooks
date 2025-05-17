package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiwBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwBooksApplication.class, args);
		//per criptare password da passare in import.sql
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("admin"));
	}

}
