package com.test.datamanagement.repository;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadBRepository extends JpaRepository<WorkloadB, Long> {
  WorkloadB findFirstByTestConfig(TestConfig testConfig);
}
