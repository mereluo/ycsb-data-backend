package com.test.datamanagement.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class TimeSeriesData implements Serializable {
  private List<Integer> time;
  private List<Double> latency;
}
