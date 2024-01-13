package com.test.datamanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestWorkloadA {
  private int concurrencyLevel; // 64, 128, 256
  private int recordCounts;
  private String commandLine;

  private boolean isTransactional; // ycsb or ycsb-t
  private String platform;
  private int numOfNodes;
  private boolean isMultiRegion;
  private int numOfRegions;
  private String description;

  private String database;
}
