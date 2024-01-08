package com.test.datamanagement.repository;

import com.test.datamanagement.entity.DatabaseOption;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseOptionRepository extends JpaRepository<DatabaseOption, Long> {
  DatabaseOption findFirstByDatabase(String name);
}
