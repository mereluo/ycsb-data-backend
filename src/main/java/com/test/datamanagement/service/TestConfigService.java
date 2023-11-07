package com.test.datamanagement.service;

import com.test.datamanagement.entity.TestConfig;
import java.util.List;
import java.util.Optional;

public interface TestConfigService {
  List<TestConfig> findAllEntity();
  Optional<TestConfig> findById(Long id);
  Optional<TestConfig> findByCommandLine(String commandLine);
  TestConfig saveEntity(TestConfig testConfig);
  TestConfig updateEntity(TestConfig testConfig);
  void deleteEntity(Long id);
}
