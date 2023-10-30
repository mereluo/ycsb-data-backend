package com.test.datamanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
@DiscriminatorValue("F")
@Table(name="workload_F")
public class WorkloadF{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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

  @OneToOne
  @JoinColumn(name = "test_config_id")
  private TestConfig testConfigF;
}