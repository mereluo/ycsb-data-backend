package com.test.datamanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="workload_F")
public class WorkloadF{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String workloadType = "F";
  private double opsPerSec;
  private double readMeanLatency;
  private double readMaxLatency;
  private double readP95;
  private double readP99;
  private double rmwMeanLatency;
  private double rmwMaxLatency;
  private double rmwP95;
  private double rmwP99;
  // A column that uses Json or other dt to store Time series
  @Column(columnDefinition = "jsonb")
  private String timeSeries;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "test_config_id")
  private TestConfig testConfig;

  public WorkloadF(double opsPerSec, double readMeanLatency, double readMaxLatency, double readP95,
      double readP99, double rmwMeanLatency, double rmwMaxLatency, double rmwP95, double rmwP99,
      String timeSeries, TestConfig testConfig) {
    this.opsPerSec = opsPerSec;
    this.readMeanLatency = readMeanLatency;
    this.readMaxLatency = readMaxLatency;
    this.readP95 = readP95;
    this.readP99 = readP99;
    this.rmwMeanLatency = rmwMeanLatency;
    this.rmwMaxLatency = rmwMaxLatency;
    this.rmwP95 = rmwP95;
    this.rmwP99 = rmwP99;
    this.timeSeries = timeSeries;
    this.testConfig = testConfig;
  }
}