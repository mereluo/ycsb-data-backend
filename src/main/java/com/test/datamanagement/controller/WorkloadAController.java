package com.test.datamanagement.controller;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import com.test.datamanagement.model.CompleteWorkloadA;
import com.test.datamanagement.model.RequestWorkloadA;
import com.test.datamanagement.service.DBConfigService;
import com.test.datamanagement.service.DBOptionService;
import com.test.datamanagement.service.TestConfigService;
import com.test.datamanagement.service.WorkloadAService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/workloadA")
public class WorkloadAController {

  private final DBOptionService dbOptionService;
  private final DBConfigService dbConfigService;
  private final TestConfigService testConfigService;
  private final WorkloadAService workloadAService;

  public WorkloadAController(WorkloadAService workloadAService, DBOptionService dbOptionService,
      DBConfigService dbConfigService, TestConfigService testConfigService) {
    this.workloadAService = workloadAService;
    this.dbOptionService = dbOptionService;
    this.dbConfigService = dbConfigService;
    this.testConfigService = testConfigService;
  }
  @GetMapping("")
  public List<WorkloadA> findAllEntity() {
    return workloadAService.findAllEntity();
  }

  @GetMapping("/{id}")
  public Optional<WorkloadA> findEntityById(@PathVariable("id") Long id) {
    return workloadAService.findById(id);
  }

  @PostMapping
  public WorkloadA saveEntity(@RequestBody WorkloadA workloadA) {
    return workloadAService.saveEntity(workloadA);
  }

  @PostMapping("/createA")
  public WorkloadA createA(@RequestBody CompleteWorkloadA entity) {
    DatabaseOption dbOption = new DatabaseOption(entity.getDatabase());
    dbOption = dbOptionService.saveEntity(dbOption);

    DBConfig dbConfig = entity.getDBConfig(dbOption);
    dbConfig = dbConfigService.saveEntity(dbConfig);

    TestConfig testConfig = entity.getTestConfig(dbConfig);
    testConfig = testConfigService.saveEntity(testConfig);

    WorkloadA workloadA = entity.getWorkloadA(testConfig);
    return workloadAService.saveEntity(workloadA);
  }

  @PostMapping("/retrieveA")
  public WorkloadA retrieveA(@RequestBody RequestWorkloadA entity) {
    // database
    DatabaseOption dbOption = dbOptionService.findFirstByDatabase(entity.getDatabase());
    // dbConfig
    List<DBConfig> dbConfigLists = dbConfigService.findAllByDatabaseOption(dbOption);
    DBConfig foundDbConfig = null;
    for (DBConfig curr : dbConfigLists) {
      if (curr.isTransactional() == entity.isTransactional()
          && Objects.equals(curr.getPlatform(), entity.getPlatform())
          && curr.getNumOfNodes() == entity.getNumOfNodes()
          && curr.isMultiRegion() == entity.isMultiRegion()
          && curr.getNumOfRegions() == entity.getNumOfRegions()
          && Objects.equals(curr.getDescription(), entity.getDescription())) {
        foundDbConfig = curr;
        break;
      }
    }
    // testConfig
    TestConfig foundTestConfig = null;
    List<TestConfig> testConfigs = testConfigService.findAllByDbConfig(foundDbConfig);
    for (TestConfig curr : testConfigs) {
      if (curr.getConcurrencyLevel() == entity.getConcurrencyLevel()
      && curr.getRecordCounts() == entity.getRecordCounts()
      && curr.getCommandLine().equals(entity.getCommandLine())) {
        foundTestConfig = curr;
        break;
      }
    }
    // workload
    return workloadAService.findFirstByTestConfigA(foundTestConfig);
  }

  @PutMapping
  public WorkloadA updateEntity(@RequestBody WorkloadA workloadA) {
    return workloadAService.updateEntity(workloadA);
  }
  @DeleteMapping("/{id}")
  public void deleteEntity(@PathVariable("id") Long id) {
    workloadAService.deleteEntity(id);
  }
}
