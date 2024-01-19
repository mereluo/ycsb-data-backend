package com.test.datamanagement.repository;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadARepository extends JpaRepository<WorkloadA, Long> {
  WorkloadA findFirstByTestConfig(TestConfig testConfig);
}
