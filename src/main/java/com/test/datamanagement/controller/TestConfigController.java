package com.test.datamanagement.controller;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.service.TestConfigService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/commandLine/{commandLine}")
  public TestConfig findByCommandLine(@PathVariable("commandLine") String commandLine) {
    return TestConfigService.findByCommandLine(commandLine);
  }
  @PostMapping
  public TestConfig saveEntity(@RequestBody TestConfig TestConfig) {
    return TestConfigService.saveEntity(TestConfig);
  }
}
