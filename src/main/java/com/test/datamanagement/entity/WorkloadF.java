package com.test.datamanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("F")
@Table(name="workload_F")
public class WorkloadF extends Workload {
  private double rmwMeanLatency;
  private double rmwMaxLatency;
  private double rmwP95;
  private double rmwP99;
  // A column that uses Json or other dt to store Time series
  @Column(columnDefinition = "jsonb")
  private String timeSeries;

}