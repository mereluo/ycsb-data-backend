package com.test.datamanagement.service.impl;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import com.test.datamanagement.repository.WorkloadARepository;
import com.test.datamanagement.service.WorkloadAService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WorkloadAServiceImpl implements WorkloadAService {

  private final WorkloadARepository workloadARepository;
  public WorkloadAServiceImpl(WorkloadARepository workloadARepository) {
    this.workloadARepository = workloadARepository;
  }
  @Override
  public List<WorkloadA> findAllEntity() {
    return workloadARepository.findAll();
  }
  @Override
  public Optional<WorkloadA> findById(Long id) {
    return workloadARepository.findById(id);
  }

  public WorkloadA findFirstByTestConfig(TestConfig testConfig) {
    return workloadARepository.findFirstByTestConfig(testConfig);
  }
  @Override
  public WorkloadA saveEntity(WorkloadA workloadA) {
    WorkloadA entity = findFirstByTestConfig(workloadA.getTestConfig());
    if (entity != null && entity.equals(workloadA)) {
      return entity;
    }
    return workloadARepository.save(workloadA);
  }
  @Override
  public WorkloadA updateEntity(WorkloadA workloadA) {
    return workloadARepository.save(workloadA);
  }
  @Override
  public void deleteEntity(Long id) {
    workloadARepository.deleteById(id);
  }

}
