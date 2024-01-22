package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadF;
import com.test.datamanagement.repository.WorkloadFRepository;
import com.test.datamanagement.service.WorkloadFService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WorkloadFServiceImpl implements WorkloadFService {

  private final WorkloadFRepository workloadFRepository;
  public WorkloadFServiceImpl(WorkloadFRepository workloadFRepository) {
    this.workloadFRepository = workloadFRepository;
  }
  @Override
  public List<WorkloadF> findAllEntity() {
    return workloadFRepository.findAll();
  }
  @Override
  public Optional<WorkloadF> findById(Long id) {
    return workloadFRepository.findById(id);
  }

  public WorkloadF findFirstByTestConfig(TestConfig testConfig) {
    return workloadFRepository.findFirstByTestConfig(testConfig);
  }
  @Override
  public WorkloadF saveEntity(WorkloadF workloadF) {
    WorkloadF entity = findFirstByTestConfig(workloadF.getTestConfig());
    if (entity != null && entity.equals(workloadF)) {
      return entity;
    }
    return workloadFRepository.save(workloadF);
  }
  @Override
  public WorkloadF updateEntity(WorkloadF workloadF) {
    return workloadFRepository.save(workloadF);
  }
  @Override
  public void deleteEntity(Long id) {
    workloadFRepository.deleteById(id);
  }

}

