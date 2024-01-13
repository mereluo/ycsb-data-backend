package com.test.datamanagement.repository;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBConfigRepository extends JpaRepository<DBConfig, Long> {
  DBConfig findFirstByDescription(String description);
  List<DBConfig> findAllByDatabaseOption(DatabaseOption databaseOption);
}
