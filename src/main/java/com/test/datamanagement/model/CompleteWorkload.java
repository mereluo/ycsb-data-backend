package com.test.datamanagement.model;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.entity.TestConfig;
import com.test.datamanagement.entity.WorkloadA;
import com.test.datamanagement.entity.WorkloadB;
import com.test.datamanagement.entity.WorkloadF;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompleteWorkload {
  private String workloadType;
  // workload A fields
  private double opsPerSec;
  private double readMeanLatency;
  private double readMaxLatency;
  private double readP95;
  private double readP99;
  private double updateMeanLatency;
  private double updateMaxLatency;
  private double updateP95;
  private double updateP99;

  private double rmwMeanLatency;
  private double rmwMaxLatency;
  private double rmwP95;
  private double rmwP99;

  private TimeSeries timeSeries;

  // testConfig fields
  private int concurrencyLevel; // 64, 128, 256 maybe an enum?
  private int recordCounts;
  private String commandLine;

  // dbConfig fields
  private boolean isTransactional; // ycsb or ycsb-t
  private String platform;
  private int numOfNodes;
  private boolean isMultiRegion;
  private int numOfRegions;
  private String description;

  // databaseOption fields
  private String database;

  public DBConfig getDBConfig(DatabaseOption dbOption) {
//    if (!isMultiRegion && numOfRegions > 1) {
//      throw new IllegalArgumentException("Error: inconsistent isMultiRegion and numOfRegions");
//    }
    return new DBConfig(isTransactional, platform, numOfNodes, isMultiRegion,
    numOfRegions, description, dbOption);
  }

  public TestConfig getTestConfig(DBConfig dbConfig) {
    return new TestConfig(concurrencyLevel, recordCounts, commandLine, dbConfig);
  }

  public WorkloadA getWorkloadA(TestConfig testConfig) {
    return new WorkloadA(opsPerSec, readMeanLatency, readMaxLatency, readP95,
    readP99, updateMeanLatency, updateMaxLatency, updateP95,
    updateP99, timeSeries, testConfig);
  }

  public WorkloadB getWorkloadB(TestConfig testConfig) {
    return new WorkloadB(opsPerSec, readMeanLatency, readMaxLatency, readP95,
    readP99, updateMeanLatency, updateMaxLatency, updateP95,
    updateP99, timeSeries, testConfig);
  }

  public WorkloadF getWorkloadF(TestConfig testConfig) {
    return new WorkloadF(opsPerSec, readMeanLatency, readMaxLatency, readP95,
    readP99, rmwMeanLatency, rmwMaxLatency, rmwP95, rmwP99,
    timeSeries, testConfig);
  }

}
