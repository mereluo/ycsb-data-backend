package com.test.datamanagement.service;

import com.test.datamanagement.entity.DatabaseOption;
import java.util.List;
import java.util.Optional;

public interface DatabaseOptionService {
  List<DatabaseOption> findAllEntity();
  Optional<DatabaseOption> findById(Long id);
  DatabaseOption saveEntity(DatabaseOption performanceEntity);
  DatabaseOption updateEntity(DatabaseOption performanceEntity);
  void deleteEntity(Long id);

  //    Using Request for Save and Update Employee
}
