package com.test.datamanagement.repository;

import com.test.datamanagement.entity.WorkloadA;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadARepository extends JpaRepository<WorkloadA, Long> {
  WorkloadA findFirstByOrderByIdDesc();
}
