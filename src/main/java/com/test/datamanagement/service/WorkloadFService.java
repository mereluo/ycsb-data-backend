package com.test.datamanagement.service;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadF;
import java.util.List;
import java.util.Optional;

public interface WorkloadFService {
  List<WorkloadF> findAllEntity();
  Optional<WorkloadF> findById(Long id);
  WorkloadF saveEntity(WorkloadF workloadF);
  WorkloadF findFirstByTestConfig(TestConfig testConfig);
  WorkloadF updateEntity(WorkloadF workloadF);
  void deleteEntity(Long id);
}
