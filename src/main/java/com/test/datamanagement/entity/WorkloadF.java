package com.test.datamanagement.entity;

import com.test.datamanagement.model.JsonConverter;
import com.test.datamanagement.model.TimeSeries;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
  @JdbcTypeCode(SqlTypes.JSON)
  private TimeSeries timeSeries;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "test_config_id")
  private TestConfig testConfig;

  public WorkloadF(double opsPerSec, double readMeanLatency, double readMaxLatency, double readP95,
      double readP99, double rmwMeanLatency, double rmwMaxLatency, double rmwP95, double rmwP99,
      TimeSeries timeSeries, TestConfig testConfig) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkloadF workloadF = (WorkloadF) o;
    return Double.compare(getOpsPerSec(), workloadF.getOpsPerSec()) == 0
        && Double.compare(getReadMeanLatency(), workloadF.getReadMeanLatency()) == 0
        && Double.compare(getReadMaxLatency(), workloadF.getReadMaxLatency()) == 0
        && Double.compare(getReadP95(), workloadF.getReadP95()) == 0
        && Double.compare(getReadP99(), workloadF.getReadP99()) == 0
        && Double.compare(getRmwMeanLatency(), workloadF.getRmwMeanLatency()) == 0
        && Double.compare(getRmwMaxLatency(), workloadF.getRmwMaxLatency()) == 0
        && Double.compare(getRmwP95(), workloadF.getRmwP95()) == 0
        && Double.compare(getRmwP99(), workloadF.getRmwP99()) == 0
        && Objects.equals(getWorkloadType(), workloadF.getWorkloadType())
        && Objects.equals(getTimeSeries(), workloadF.getTimeSeries())
        && Objects.equals(getTestConfig(), workloadF.getTestConfig());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWorkloadType(), getOpsPerSec(), getReadMeanLatency(),
        getReadMaxLatency(),
        getReadP95(), getReadP99(), getRmwMeanLatency(), getRmwMaxLatency(), getRmwP95(),
        getRmwP99(),
        getTimeSeries(), getTestConfig());
  }
}