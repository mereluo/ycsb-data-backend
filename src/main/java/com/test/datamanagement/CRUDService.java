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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * CRUD operations for the database
 * Only created CREATE AND FIND
 * TODO: create deleteByDatabase, deleteByDescription, deleteByCommandLine
 */
@Service
public class CRUDService {
  private final RestTemplate restTemplate;

  public CRUDService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
 * Finds the first DatabaseOption entity with a matching name.
 *
 * @param nameToFind The name of the DatabaseOption to find.
 * @return The DatabaseOption entity if found, otherwise null.
 */
  public DatabaseOption findFirstByDatabase(String nameToFind) {
    String url = "http://localhost:8080/api/dbOption/name/{name}";
    try {
      ResponseEntity<DatabaseOption> response = restTemplate.getForEntity(url, DatabaseOption.class, nameToFind);
      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
        return response.getBody();
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
    }
    return null;
  }
  
  /**
 * Creates a new DatabaseOption entity if one with the given name does not exist.
 *
 * @param dbName The name of the DatabaseOption to create.
 */
  public void createDBOption(String dbName) {
    if (findFirstByDatabase(dbName) == null) {
      String url = "http://localhost:8080/api/dbOption";
      DatabaseOption newEntity = new DatabaseOption();
      newEntity.setDatabase(dbName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<DatabaseOption> requestEntity = new HttpEntity<>(newEntity, headers);

      try {
        restTemplate.postForObject(url, requestEntity, DatabaseOption.class);
      } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
      }
    }
  }

  /**
 * Finds a DBConfig entity based on its description.
 *
 * @param description The description to search for in DBConfig entities.
 * @return The DBConfig entity if found, otherwise null.
 */
  public DBConfig findDBConfigByDescription(String description) {
    String url = "http://localhost:8080/api/dbConfig/description/{description}";
    try {
      ResponseEntity<DBConfig> response = restTemplate.getForEntity(url, DBConfig.class, description);
      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
        return response.getBody();
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
    }
    return null; 
  }

  /**
 * Creates a new DBConfig entity with the specified properties if one with the given description does not exist.
 *
 * @param isTransactional Whether the DBConfig is transactional.
 * @param platform The platform of the DBConfig.
 * @param numOfNodes The number of nodes in the DBConfig.
 * @param isMultiRegion Whether the DBConfig is multi-region.
 * @param numOfRegions The number of regions in the DBConfig.
 * @param description The description of the DBConfig.
 * @param dbName The name of the associated database.
 */
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

      try {
        restTemplate.postForObject(url, requestEntity, DBConfig.class);
      } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
      }
    }
  }

  /**
 * Creates a new TestConfig entity with the specified properties if one with the given command line does not exist.
 *
 * @param concurrencyLevel The concurrency level of the TestConfig.
 * @param recordCounts The record counts for the TestConfig.
 * @param commandLine The command line associated with the TestConfig.
 * @param description The description of the TestConfig.
 */
  public void createTestConfig(int concurrencyLevel, int recordCounts, String commandLine,
      String description) {
    if (findTestConfigByCommandLine(commandLine) == null) {
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

      try {
        restTemplate.postForObject(url, requestEntity, TestConfig.class);
      } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
      }
    }
  }

  /**
   * Finds a TestConfig by its command line.
   *
   * @param commandLine The command line to search for.
   * @return The found TestConfig, or null if not found or in case of an error.
   */
  public TestConfig findTestConfigByCommandLine(String commandLine) {
    String url = "http://localhost:8080/api/testConfig/commandLine/{commandLine}";
    try {
      ResponseEntity<TestConfig> response = restTemplate.getForEntity(url, TestConfig.class, commandLine);
      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
        return response.getBody();
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
    }
    return null; 
  }

  /**
   * Creates a new WorkloadA entity with given performance metrics and time series data.
   *
   * @param opsPerSec The operations per second.
   * @param readMeanLatency The mean latency for read operations.
   * @param readMaxLatency The max latency for read operations.
   * @param readP95 The 95th percentile latency for read operations.
   * @param readP99 The 99th percentile latency for read operations.
   * @param updateMeanLatency The mean latency for update operations.
   * @param updateMaxLatency The max latency for update operations.
   * @param updateP95 The 95th percentile latency for update operations.
   * @param updateP99 The 99th percentile latency for update operations.
   * @param timeSeries The time series data.
   * @param commandLine The command line configuration.
   */
  public void createWorkloadA(double opsPerSec, double readMeanLatency, double readMaxLatency,
      double readP95, double readP99, double updateMeanLatency, double updateMaxLatency,
      double updateP95, double updateP99, TimeSeries timeSeries, String commandLine) {
    String url = "http://localhost:8080/api/workloadA";
    WorkloadA newEntity = new WorkloadA();
    // Set the properties for the new entity
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
    newEntity.setTestConfigA(findTestConfigByCommandLine(commandLine));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<WorkloadA> requestEntity = new HttpEntity<>(newEntity, headers);

    try {
      restTemplate.postForObject(url, requestEntity, WorkloadA.class);
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the most recently created WorkloadA entity.
   *
   * @return The latest WorkloadA entity, or null if not found or in case of an error.
   */
  public WorkloadA findLatestWorkloadA() {
    String url = "http://localhost:8080/api/workloadA/latest";
    try {
      ResponseEntity<WorkloadA> response = restTemplate.getForEntity(url, WorkloadA.class);
      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null)
        return response.getBody();
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
    }
    return null; 
  }

// ++++++++++++++++++++++ Added New Functions needs further test +++++++++++++++++++++++++++
  /**
   * Deletes a DatabaseOption entity with the specified database name.
   *
   * @param dbName The name of the database to be deleted.
   * @return true if the deletion was successful; false otherwise.
   */
  public boolean deleteByDatabase(String dbName) {
    String url = "http://localhost:8080/api/dbOption/{name}";
    try {
      restTemplate.delete(url, dbName);
      return true;
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Deletes a DBConfig entity with the specified description.
   *
   * @param description The description of the DBConfig to be deleted.
   * @return true if the deletion was successful; false otherwise.
   */
  public boolean deleteByDescription(String description) {
    String url = "http://localhost:8080/api/dbConfig/description/{description}";
    try {
      restTemplate.delete(url, description);
      return true;
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Deletes a TestConfig entity with the specified command line.
   *
   * @param commandLine The command line of the TestConfig to be deleted.
   * @return true if the deletion was successful; false otherwise.
   */
  public boolean deleteByCommandLine(String commandLine) {
    String url = "http://localhost:8080/api/testConfig/commandLine/{commandLine}";
    try {
      restTemplate.delete(url, commandLine);
      return true;
    } catch (Exception e) {
      // Log the exception
      e.printStackTrace();
      return false;
    }
  }

}
