package com.test.datamanagement.repository;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadFRepository extends JpaRepository<WorkloadF, Long> {
  WorkloadF findFirstByTestConfig(TestConfig testConfig);
}