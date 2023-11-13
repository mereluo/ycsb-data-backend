package com.test.datamanagement.controller;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.service.TestConfigService;
import java.util.List;
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
@RequestMapping("/api/testConfig")
public class TestConfigController {
  private final TestConfigService TestConfigService;

  public TestConfigController(TestConfigService TestConfigService) {
    this.TestConfigService = TestConfigService;
  }

  @GetMapping("")
  public List<TestConfig> findAllEntity() {
    return TestConfigService.findAllEntity();
  }

  @GetMapping("/{id}")
  public Optional<TestConfig> findEntityById(@PathVariable("id") Long id) {
    return TestConfigService.findById(id);
  }
  @GetMapping("/commandLine/{commandLine}")
  public TestConfig findByCommandLine(@PathVariable("commandLine") String commandLine) {
    return TestConfigService.findByCommandLine(commandLine);
  }
  @PostMapping
  public TestConfig saveEntity(@RequestBody TestConfig TestConfig) {
    TestConfig entity =  TestConfigService.findByCommandLine(TestConfig.getCommandLine());
    if (entity!= null && entity.equals(TestConfig)) {
      return entity;
    }
    return TestConfigService.saveEntity(TestConfig);
  }
  @PutMapping
  public TestConfig updateEntity(@RequestBody TestConfig TestConfig) {
    return TestConfigService.updateEntity(TestConfig);
  }
  @DeleteMapping("/{id}")
  public void deleteEntity(@PathVariable("id") Long id) {
    TestConfigService.deleteEntity(id);
  }
}
