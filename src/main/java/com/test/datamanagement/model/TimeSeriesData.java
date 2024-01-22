package com.test.datamanagement.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class TimeSeriesData {
  private List<Integer> time;
  private List<Double> latency;
}
