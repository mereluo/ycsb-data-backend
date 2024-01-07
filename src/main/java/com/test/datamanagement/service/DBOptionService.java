package com.test.datamanagement.service;

import com.test.datamanagement.entity.DatabaseOption;
import java.util.List;
import java.util.Optional;

public interface DBOptionService {
  List<DatabaseOption> findAllEntity();
  Optional<DatabaseOption> findById(Long id);
  DatabaseOption findFirstByDatabase(String name);
  DatabaseOption saveEntity(DatabaseOption databaseOption);
  DatabaseOption updateEntity(DatabaseOption databaseOption);

  void deleteEntity(Long id);
}
