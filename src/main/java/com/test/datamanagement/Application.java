package com.test.datamanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		RestTemplate restTemplate = new RestTemplate();
		ConsoleService console = new ConsoleService(restTemplate);
//		String dbName = "Spanner";
//		console.createDBOption(dbName);
//		System.out.println(console.getOptionIDByName(dbName));
		console.createDBConfig(false, "Platform", 2,
		true, 2, "Description", "Spanner");
	}

}
