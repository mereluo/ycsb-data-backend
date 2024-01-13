package com.test.datamanagement.repository;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestConfigRepository extends JpaRepository<TestConfig, Long> {
  TestConfig findFirstByCommandLine(String commandLine);
  List<TestConfig> findAllByDbConfig(DBConfig config);
}
