package com.test.datamanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="db_config")
public class DBConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean isTransactional; // ycsb or ycsb-t
  private String platform;
  private int numOfNodes;
  private boolean isMultiRegion;
  private int numOfRegions;
  private String description;

  // One DBConfig can have many TestConfig entities
  @OneToMany(mappedBy = "dbConfig",
      cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<TestConfig> testConfigs;

  // Many DBConfig entities belong to one DatabaseOption
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_database_id") // This is the foreign key column in the db_config table
  private DatabaseOption databaseOption;
}
