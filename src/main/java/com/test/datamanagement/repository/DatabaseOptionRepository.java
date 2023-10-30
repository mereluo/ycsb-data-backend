package com.test.datamanagement.repository;

import com.test.datamanagement.entity.DatabaseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseOptionRepository extends JpaRepository<DatabaseOption, Long> {

}
