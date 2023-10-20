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
@DiscriminatorValue("A")
@Table(name="workload_A")
public class WorkloadA extends Workload {
  private double updateMeanLatency;
  private double updateMaxLatency;
  private double updateP95;
  private double updateP99;
  // A column that uses Json or other dt to store Time series
  @Column(columnDefinition = "jsonb")
  private String timeSeries;
}