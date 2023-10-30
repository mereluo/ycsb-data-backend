package com.test.datamanagement.service;

import com.test.datamanagement.entity.DBConfig;
import java.util.List;
import java.util.Optional;

public interface DBConfigService {
  List<DBConfig> findAllEntity();
  Optional<DBConfig> findById(Long id);
  DBConfig saveEntity(DBConfig dbConfig);
  DBConfig updateEntity(DBConfig dbConfig);
  void deleteEntity(Long id);
}
