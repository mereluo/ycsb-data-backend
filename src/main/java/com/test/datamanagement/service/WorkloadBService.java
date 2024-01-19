package com.test.datamanagement.service;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadB;
import java.util.List;
import java.util.Optional;

public interface WorkloadBService {
  List<WorkloadB> findAllEntity();
  Optional<WorkloadB> findById(Long id);
  WorkloadB saveEntity(WorkloadB workloadB);
  WorkloadB findFirstByTestConfig(TestConfig testConfig);
  WorkloadB updateEntity(WorkloadB workloadB);
  void deleteEntity(Long id);
}
