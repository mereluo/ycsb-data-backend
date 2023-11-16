package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.DBConfig;
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
  @Override
  public Optional<DBConfig> findById(Long id) {
    return dbConfigRepository.findById(id);
  }
  @Override
  public DBConfig findByDescription(String description) {
    return dbConfigRepository.findFirstByDescription(description);
  }
  @Override
  public DBConfig saveEntity(DBConfig DBConfig) {
    return dbConfigRepository.save(DBConfig);
  }
  @Override
  public DBConfig updateEntity(DBConfig DBConfig) {
    return dbConfigRepository.save(DBConfig);
  }
  @Override
  public void deleteEntity(Long id) {
    dbConfigRepository.deleteById(id);
  }

}
