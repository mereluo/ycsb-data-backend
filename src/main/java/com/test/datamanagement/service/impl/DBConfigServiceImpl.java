package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.repository.DBConfigRepository;
import com.test.datamanagement.service.DBConfigService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DBConfigServiceImpl implements DBConfigService {
  private final DBConfigRepository dbConfigRepository;

  public DBConfigServiceImpl(DBConfigRepository dbConfigRepository) {
    this.dbConfigRepository = dbConfigRepository;
  }
  @Override
  public List<DBConfig> findAllEntity() {
    return dbConfigRepository.findAll();
  }

  public List<DBConfig> findAllByDatabaseOption(DatabaseOption databaseOption) {
    return dbConfigRepository.findAllByDatabaseOption(databaseOption);
  }
  @Override
  public Optional<DBConfig> findById(Long id) {
    return dbConfigRepository.findById(id);
  }
  @Override
  public DBConfig findByDescription(String description) {
    return dbConfigRepository.findFirstByDescription(description);
  }
  @Override
  public DBConfig saveEntity(DBConfig dbConfig) {
    DBConfig entity = findByDescription(dbConfig.getDescription());
    if (entity != null && entity.equals(dbConfig)) {
      return entity;
    }
    return dbConfigRepository.save(dbConfig);
  }
  @Override
  public DBConfig updateEntity(DBConfig dbConfig) {
    return dbConfigRepository.save(dbConfig);
  }
  @Override
  public void deleteEntity(Long id) {
    dbConfigRepository.deleteById(id);
  }

}
