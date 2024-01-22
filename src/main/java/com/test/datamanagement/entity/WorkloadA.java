package com.test.datamanagement.entity;

import com.test.datamanagement.model.JsonConverter;
import com.test.datamanagement.model.TimeSeries;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name="workload_A")
public class WorkloadA {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String workloadType = "A";
  private double opsPerSec;
  private double readMeanLatency;
  private double readMaxLatency;
  private double readP95;
  private double readP99;
  private double updateMeanLatency;
  private double updateMaxLatency;
  private double updateP95;
  private double updateP99;

  // A column that uses Json or other dt to store Time series
  @JdbcTypeCode(SqlTypes.JSON)
  private TimeSeries timeSeries;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "test_config_id")
  private TestConfig testConfig;

  public WorkloadA(double opsPerSec, double readMeanLatency, double readMaxLatency, double readP95,
      double readP99, double updateMeanLatency, double updateMaxLatency, double updateP95,
      double updateP99, TimeSeries timeSeries, TestConfig testConfig) {
    this.opsPerSec = opsPerSec;
    this.readMeanLatency = readMeanLatency;
    this.readMaxLatency = readMaxLatency;
    this.readP95 = readP95;
    this.readP99 = readP99;
    this.updateMeanLatency = updateMeanLatency;
    this.updateMaxLatency = updateMaxLatency;
    this.updateP95 = updateP95;
    this.updateP99 = updateP99;
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
    WorkloadA workloadA = (WorkloadA) o;
    return Double.compare(getOpsPerSec(), workloadA.getOpsPerSec()) == 0
        && Double.compare(getReadMeanLatency(), workloadA.getReadMeanLatency()) == 0
        && Double.compare(getReadMaxLatency(), workloadA.getReadMaxLatency()) == 0
        && Double.compare(getReadP95(), workloadA.getReadP95()) == 0
        && Double.compare(getReadP99(), workloadA.getReadP99()) == 0
        && Double.compare(getUpdateMeanLatency(), workloadA.getUpdateMeanLatency()) == 0
        && Double.compare(getUpdateMaxLatency(), workloadA.getUpdateMaxLatency()) == 0
        && Double.compare(getUpdateP95(), workloadA.getUpdateP95()) == 0
        && Double.compare(getUpdateP99(), workloadA.getUpdateP99()) == 0
        && Objects.equals(getTimeSeries(), workloadA.getTimeSeries())
        && Objects.equals(getTestConfig(), workloadA.getTestConfig());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOpsPerSec(), getReadMeanLatency(), getReadMaxLatency(), getReadP95(),
        getReadP99(), getUpdateMeanLatency(), getUpdateMaxLatency(), getUpdateP95(), getUpdateP99(),
        getTimeSeries(), getTestConfig());
  }
}