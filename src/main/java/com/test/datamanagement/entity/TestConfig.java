package com.test.datamanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="test_config")
public class TestConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int concurrencyLevel; // 64, 128, 256 maybe an enum?
  private int recordCounts;
  private String commandLine;

  // Many TestConfig entities belong to one DBConfig
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_db_config_id") // This is the foreign key column in the test_config table
  private DBConfig dbConfig;
}
