package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadB;
import com.test.datamanagement.repository.WorkloadBRepository;
import com.test.datamanagement.service.WorkloadBService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WorkloadBServiceImpl implements WorkloadBService {

  private final WorkloadBRepository workloadBRepository;
  public WorkloadBServiceImpl(WorkloadBRepository workloadBRepository) {
    this.workloadBRepository = workloadBRepository;
  }
  @Override
  public List<WorkloadB> findAllEntity() {
    return workloadBRepository.findAll();
  }
  @Override
  public Optional<WorkloadB> findById(Long id) {
    return workloadBRepository.findById(id);
  }

  public WorkloadB findFirstByTestConfig(TestConfig testConfig) {
    return workloadBRepository.findFirstByTestConfig(testConfig);
  }
  @Override
  public WorkloadB saveEntity(WorkloadB workloadB) {
    WorkloadB entity = findFirstByTestConfig(workloadB.getTestConfig());
    if (entity != null && entity.equals(workloadB)) {
      return entity;
    }
    return workloadBRepository.save(workloadB);
  }
  @Override
  public WorkloadB updateEntity(WorkloadB workloadB) {
    return workloadBRepository.save(workloadB);
  }
  @Override
  public void deleteEntity(Long id) {
    workloadBRepository.deleteById(id);
  }

}

