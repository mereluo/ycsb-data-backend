package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.repository.TestConfigRepository;
import com.test.datamanagement.service.TestConfigService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TestConfigServiceImpl implements TestConfigService {
  private final TestConfigRepository testConfigRepository;

  public TestConfigServiceImpl(TestConfigRepository testConfigRepository) {
    this.testConfigRepository = testConfigRepository;
  }
  @Override
  public List<TestConfig> findAllEntity() {
    return testConfigRepository.findAll();
  }

  public List<TestConfig> findAllByDbConfig(DBConfig dbConfig) {
    return testConfigRepository.findAllByDbConfig(dbConfig);
  }
  @Override
  public Optional<TestConfig> findById(Long id) {
    return testConfigRepository.findById(id);
  }
  @Override
  public TestConfig findByCommandLine(String commandLine) {
    return testConfigRepository.findFirstByCommandLine(commandLine);
  }
  @Override
  public TestConfig saveEntity(TestConfig testConfig) {
    TestConfig entity = findByCommandLine(testConfig.getCommandLine());
    if (entity != null && entity.equals(testConfig)) {
      return entity;
    }
    return testConfigRepository.save(testConfig);
  }
  @Override
  public TestConfig updateEntity(TestConfig testConfig) {
    return testConfigRepository.save(testConfig);
  }
  @Override
  public void deleteEntity(Long id) {
    testConfigRepository.deleteById(id);
  }

}
