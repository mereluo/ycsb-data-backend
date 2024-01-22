package com.test.datamanagement.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TimeSeries implements Serializable {
  Map<String, TimeSeriesData> data;

  public TimeSeries() {
    this.data = new HashMap<>();
  }
}