package com.test.datamanagement;
import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import com.test.datamanagement.model.TimeSeries;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CRUDService {
  private final RestTemplate restTemplate;

  public CRUDService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public DatabaseOption findFirstByDatabase(String nameToFind) {
    String url = "http://localhost:8080/api/dbOption/name/{name}";
    ResponseEntity<DatabaseOption> response = restTemplate.getForEntity(url, DatabaseOption.class, nameToFind);
    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
      return response.getBody();
    else
      return null;
  }
  public void createDBOption(String dbName) {
    if (findFirstByDatabase(dbName) == null) {
      String url = "http://localhost:8080/api/dbOption";
      DatabaseOption newEntity = new DatabaseOption();
      newEntity.setDatabase(dbName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<DatabaseOption> requestEntity = new HttpEntity<>(newEntity, headers);

      restTemplate.postForObject(url, requestEntity, DatabaseOption.class);
    }
  }

  public DBConfig findDBConfigByDescription(String description) {
    String url = "http://localhost:8080/api/dbConfig/description/{description}";
    ResponseEntity<DBConfig> response = restTemplate.getForEntity(url, DBConfig.class, description);
    if (response.getStatusCode().is2xxSuccessful() && response.getBody()!= null)
      return response.getBody();
    else
      return null;
  }
  public void createDBConfig(boolean isTransactional, String platform, int numOfNodes,
      boolean isMultiRegion, int numOfRegions, String description, String dbName) {
    if (findDBConfigByDescription(description) == null) {
      String url = "http://localhost:8080/api/dbConfig";
      DBConfig newEntity = new DBConfig();
      // create new entity
      newEntity.setTransactional(isTransactional);
      newEntity.setPlatform(platform);
      newEntity.setNumOfNodes(numOfNodes);
      newEntity.setMultiRegion(isMultiRegion);
      newEntity.setNumOfRegions(numOfRegions);
      newEntity.setDescription(description);
      // DatabaseOption's unique identifier is the database name
      newEntity.setDatabaseOption(findFirstByDatabase(dbName));
      HttpHeaders headers = new HttpHeaders();

      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<DBConfig> requestEntity = new HttpEntity<>(newEntity, headers);
      restTemplate.postForObject(url, requestEntity, DatabaseOption.class);
    }
  }

  public void createTestConfig(int concurrencyLevel, int recordCounts, String commandLine,
      String description) {
    if (getTestConfigByCommandLine(commandLine) == null) {
      String url = "http://localhost:8080/api/testConfig";
      TestConfig newEntity = new TestConfig();
      // create new entity
      newEntity.setConcurrencyLevel(concurrencyLevel);
      newEntity.setRecordCounts(recordCounts);
      newEntity.setCommandLine(commandLine);
      // DBConfig's unique identifier is the description
      newEntity.setDbConfig(findDBConfigByDescription(description));
      HttpHeaders headers = new HttpHeaders();

      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<TestConfig> requestEntity = new HttpEntity<>(newEntity, headers);
      restTemplate.postForObject(url, requestEntity, TestConfig.class);
    }
  }
  public TestConfig getTestConfigByCommandLine(String commandLine) {
    String url = "http://localhost:8080/api/testConfig/commandLine/{commandLine}";
    ResponseEntity<TestConfig> response = restTemplate.getForEntity(url, TestConfig.class, commandLine);
    if (response.getStatusCode().is2xxSuccessful() && response.getBody()!= null)
      return response.getBody();
    else
      return null;
  }
  public void createWorkloadA(double opsPerSec, double readMeanLatency, double readMaxLatency,
      double readP95, double readP99, double updateMeanLatency, double updateMaxLatency,
      double updateP95, double updateP99, TimeSeries timeSeries, String commandLine) {
    String url = "http://localhost:8080/api/workloadA";
    WorkloadA newEntity = new WorkloadA();
    // create new entity
    newEntity.setOpsPerSec(opsPerSec);
    newEntity.setReadMeanLatency(readMeanLatency);
    newEntity.setReadMaxLatency(readMaxLatency);
    newEntity.setReadP95(readP95);
    newEntity.setReadP99(readP99);
    newEntity.setUpdateMeanLatency(updateMeanLatency);
    newEntity.setUpdateMaxLatency(updateMaxLatency);
    newEntity.setUpdateP95(updateP95);
    newEntity.setUpdateP99(updateP99);
    newEntity.setTimeSeries(timeSeries);
    newEntity.setTestConfigA(getTestConfigByCommandLine(commandLine));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<WorkloadA> requestEntity = new HttpEntity<>(newEntity, headers);
    restTemplate.postForObject(url, requestEntity, WorkloadA.class);
  }

  // get the latest created workloadA
  public WorkloadA getLatestWorkloadA() {
    String url = "http://localhost:8080/api/workloadA/latest";
    ResponseEntity<WorkloadA> response = restTemplate.getForEntity(url, WorkloadA.class);
    if (response.getStatusCode().is2xxSuccessful() && response.getBody()!= null)
      return response.getBody();
    else
      return null;
  }

}
