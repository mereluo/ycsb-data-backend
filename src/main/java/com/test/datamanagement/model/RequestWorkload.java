package com.test.datamanagement.model;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.entity.TestConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestWorkload {
  // testConfig fields
  private int concurrencyLevel; // 64, 128, 256
  private int recordCounts;
  private String commandLine;

  // dbConfig fields
  private boolean isTransactional; // ycsb or ycsb-t
  private String platform;
  private int numOfNodes;
  private boolean isMultiRegion;
  private int numOfRegions;
  private String description;

  private String database;

  public DBConfig getDBConfig(DatabaseOption dbOption) {
    return new DBConfig(isTransactional, platform, numOfNodes, isMultiRegion,
        numOfRegions, description, dbOption);
  }
  public TestConfig getTestConfig(DBConfig dbConfig) {
    return new TestConfig(concurrencyLevel, recordCounts, commandLine, dbConfig);
  }
}
