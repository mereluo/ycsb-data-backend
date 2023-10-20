package com.test.datamanagement.repository;

import com.test.datamanagement.entity.DBConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBConfigRepository extends JpaRepository<DBConfig, Long> {
}
