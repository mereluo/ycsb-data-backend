package com.test.datamanagement;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsoleService {
  private final RestTemplate restTemplate;

  public ConsoleService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void createDBOption(String dbName) {
    if (getOptionByName(dbName) == null) {
      String url = "http://localhost:8080/api/dbOption";
      DatabaseOption newEntity = new DatabaseOption();
      newEntity.setDatabase(dbName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<DatabaseOption> requestEntity = new HttpEntity<>(newEntity, headers);

      restTemplate.postForObject(url, requestEntity, DatabaseOption.class);
    }
  }
  public DatabaseOption getOptionByName(String nameToFind) {
    String url = "http://localhost:8080/api/dbOption/name/{name}";
    ResponseEntity<DatabaseOption> response = restTemplate.getForEntity(url, DatabaseOption.class, nameToFind);
    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
      return response.getBody();
    else
      return null;
  }

  public void createDBConfig(boolean isTransactional, String platform, int numOfNodes,
      boolean isMultiRegion, int numOfRegions, String description, String dbName) {
    String url = "http://localhost:8080/api/dbConfig";
    DBConfig newEntity = new DBConfig();
    newEntity.setTransactional(isTransactional);
    newEntity.setPlatform(platform);
    newEntity.setNumOfNodes(numOfNodes);
    newEntity.setMultiRegion(isMultiRegion);
    newEntity.setNumOfRegions(numOfRegions);
    newEntity.setDescription(description);
    newEntity.setDatabaseOption(getOptionByName(dbName));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<DBConfig> requestEntity = new HttpEntity<>(newEntity, headers);
    restTemplate.postForObject(url, requestEntity, DatabaseOption.class);
  }
  public long getDBConfigIDByDescription() {return 0;}

  public void createTestConfig() {}
  public long getTestConfigByCommandLine() {return 0;}
  public void createWorkloadA() {}

}
