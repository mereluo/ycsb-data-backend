package com.test.datamanagement;

import com.test.datamanagement.model.TimeSeries;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		RestTemplate restTemplate = new RestTemplate();
		CRUDService crud = new CRUDService(restTemplate);
//		String dbName = "Spanner";
//		crud.createDBOption(dbName);
//		System.out.println(crud.getOptionIDByName(dbName));
//		crud.createDBConfig(false, "Platform", 2,
//		true, 2, "Description", "Spanner");
//		System.out.println(crud.getDBConfigByDescription("Description").getDescription());
//		crud.createTestConfig(2, 1000, "CommandLine", "Description");
//		System.out.println(crud.getTestConfigByCommandLine("CommandLine").getDbConfig().getId());
//		TimeSeries timeSeries = new TimeSeries();
//		timeSeries.setKey("key");
//		timeSeries.setValue("value");
//		crud.createWorkloadA(1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, timeSeries, "CommandLine");
    System.out.println(crud.getLatestWorkloadA().getId());
	}

}
