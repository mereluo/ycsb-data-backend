package com.test.datamanagement.service;

import com.test.datamanagement.entity.WorkloadA;
import java.util.List;
import java.util.Optional;

public interface WorkloadAService {
  List<WorkloadA> findAllEntity();
  Optional<WorkloadA> findById(Long id);
  WorkloadA saveEntity(WorkloadA workloadA);
  WorkloadA updateEntity(WorkloadA workloadA);
  void deleteEntity(Long id);
}
