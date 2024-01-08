package com.test.datamanagement.service.impl;

import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.repository.DatabaseOptionRepository;
import com.test.datamanagement.service.DBOptionService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DBOptionServiceImpl implements DBOptionService {
  private final DatabaseOptionRepository databaseOptionRepository;

  public DBOptionServiceImpl(DatabaseOptionRepository databaseOptionRepository) {
    this.databaseOptionRepository = databaseOptionRepository;
  }
  @Override
  public List<DatabaseOption> findAllEntity() {
    return databaseOptionRepository.findAll();
  }
  @Override
  public Optional<DatabaseOption> findById(Long id) {
    return databaseOptionRepository.findById(id);
  }
  @Override
  public DatabaseOption findFirstByDatabase(String name) {
    return databaseOptionRepository.findFirstByDatabase(name);
  }
  @Override
  public DatabaseOption saveEntity(DatabaseOption databaseOption) {
    DatabaseOption entity = findFirstByDatabase(databaseOption.getDatabase());
    if (entity != null) {
      return entity;
    }
    return databaseOptionRepository.save(databaseOption);
  }
  @Override
  public DatabaseOption updateEntity(DatabaseOption databaseOption) {
    return databaseOptionRepository.save(databaseOption);
  }
  @Override
  public void deleteEntity(Long id) {
    databaseOptionRepository.deleteById(id);
  }

}
