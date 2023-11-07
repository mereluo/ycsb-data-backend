package com.test.datamanagement.controller;

import com.test.datamanagement.entity.WorkloadA;
import com.test.datamanagement.service.WorkloadAService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workloadA")
public class WorkloadAController {
  private final WorkloadAService workloadAService;

  public WorkloadAController(WorkloadAService workloadAService) {
    this.workloadAService = workloadAService;
  }

  @GetMapping("")
  public List<WorkloadA> findAllEntity() {
    return workloadAService.findAllEntity();
  }

  @GetMapping("/{id}")
  public Optional<WorkloadA> findEntityById(@PathVariable("id") Long id) {
    return workloadAService.findById(id);
  }
  @GetMapping("/latest")
  public Optional<WorkloadA> findFirstByOrderByIdDesc() {
    return workloadAService.findFirstByOrderByIdDesc();
  }
  @PostMapping
  public WorkloadA saveEntity(@RequestBody WorkloadA workloadA) {

    return workloadAService.saveEntity(workloadA);
  }
  @PutMapping
  public WorkloadA updateEntity(@RequestBody WorkloadA workloadA) {
    return workloadAService.updateEntity(workloadA);
  }
  @DeleteMapping("/{id}")
  public void deleteEntity(@PathVariable("id") Long id) {
    workloadAService.deleteEntity(id);
  }
}
